package eu.verdelhan.ecoledirecte;

import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteException;
import eu.verdelhan.ecoledirecte.v3.classes.Eleves;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Discipline;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.EnsembleMatieres;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Notes;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Periode;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.AbsencesRetard;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.SanctionsEncouragement;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.VieScolaire;
import eu.verdelhan.ecoledirecte.v3.niveaux.Classe;
import eu.verdelhan.ecoledirecte.v3.niveaux.Etablissement;
import eu.verdelhan.ecoledirecte.v3.niveaux.Niveau;
import eu.verdelhan.ecoledirecte.v3.niveaux.Niveaux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Exporte au format JSON les informations d'eleves accessibles avec un compte professeur.
 * <p>
 * Cette classe est un utilitaire executable depuis une configuration d'IDE. Le login et le mot de passe
 * doivent etre fournis dans les arguments du programme, dans cet ordre : {@code <login> <password>}.
 * L'ecriture effective du fichier JSON est volontairement laissee commentee dans {@link #writeToJson(List)}.
 */
public class ExportAllEleves {

    /** URL de base de l'API EcoleDirecte v3. */
    private static final String API_BASE_URL = "https://api.ecoledirecte.com/v3";

    /** Libelle utilise par l'API pour identifier les punitions dans la vie scolaire. */
    private static final String PUNITION_TYPE = "Punition";

    /** Longueur attendue pour les codes de periodes exploites dans l'export. */
    private static final int EXPORTED_PERIOD_CODE_LENGTH = 4;

    /** Delai minimum ajoute avant chaque appel a l'API EcoleDirecte, en millisecondes. */
    private static final int MIN_API_CALL_DELAY_MS = 800;

    /** Delai maximum ajoute avant chaque appel a l'API EcoleDirecte, en millisecondes. */
    private static final int MAX_API_CALL_DELAY_MS = 2_500;

    /** Client EcoleDirecte authentifie utilise par toutes les requetes de l'export. */
    private final EcoleDirecteClient client;

    /**
     * Cree un exporteur utilisant le client EcoleDirecte fourni.
     *
     * @param client client EcoleDirecte authentifie
     */
    public ExportAllEleves(EcoleDirecteClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    /**
     * Point d'entree de l'export professeur.
     *
     * @param args arguments de la configuration d'execution : {@code <login> <password>}
     * @throws Exception en cas d'erreur d'authentification, d'appel API ou d'ecriture JSON
     */
    public static void main(String[] args) throws Exception {
        Identifiants identifiants = readIdentifiants(args);
        EcoleDirecteClient client = new EcoleDirecteClient(API_BASE_URL);
        attendreAvantAppelApi();
        client.authenticate(identifiants.identifiant(), identifiants.motDePasse());

        new ExportAllEleves(client).exportTeacherData();
    }

    /**
     * Exporte toutes les classes accessibles au professeur authentifie.
     *
     * @throws EcoleDirecteException en cas d'erreur lors des appels a l'API EcoleDirecte
     * @throws IOException en cas d'erreur lors de l'ecriture du JSON
     */
    public void exportTeacherData() throws EcoleDirecteException, IOException {
        writeToJson(buildClassesExportees());
    }

    /**
     * Lit les identifiants passes par la configuration d'execution de l'IDE.
     *
     * @param args arguments du programme
     * @return les identifiants a utiliser pour l'authentification
     */
    private static Identifiants readIdentifiants(String[] args) {
        if (args == null || args.length < 2 || args[0].isBlank() || args[1].isBlank()) {
            throw new IllegalArgumentException("Usage: ExportAllEleves <login> <password>");
        }
        return new Identifiants(args[0], args[1]);
    }

    /**
     * Construit l'ensemble des donnees de classes a exporter.
     *
     * @return les donnees de classes pretes a etre serialisees
     * @throws EcoleDirecteException en cas d'erreur lors des appels a l'API EcoleDirecte
     */
    private List<ClasseExportee> buildClassesExportees() throws EcoleDirecteException {
        List<ClasseExportee> classesExportees = new ArrayList<>();
        for (Classe classe : getAllTeacherClasses()) {
            if (classe != null) {
                classesExportees.add(new ClasseExportee(
                        classe.getLibelle(),
                        buildElevesExportes(classe)
                ));
            }
        }
        return classesExportees;
    }

    /**
     * Ecrit les classes exportees dans un fichier JSON.
     * <p>
     * Le contenu reste commente pour le moment : l'ecriture sera traitee ulterieurement.
     *
     * @param classesExportees classes a exporter
     * @throws IOException en cas d'erreur d'ecriture lorsque l'implementation sera activee
     */
    private void writeToJson(List<ClasseExportee> classesExportees) throws IOException {
        // TODO
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();
//
//        Files.writeString(
//                Path.of("noted-classes.json"),
//                gson.toJson(classesExportees)
//        );
    }

    /**
     * Attend un delai aleatoire avant un appel a l'API EcoleDirecte.
     * <p>
     * Cette temporisation evite d'enchainer les requetes de maniere trop reguliere ou trop rapprochee.
     *
     * @throws EcoleDirecteException si le thread est interrompu pendant l'attente
     */
    private static void attendreAvantAppelApi() throws EcoleDirecteException {
        int delai = ThreadLocalRandom.current().nextInt(MIN_API_CALL_DELAY_MS, MAX_API_CALL_DELAY_MS + 1);
        try {
            Thread.sleep(delai);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new EcoleDirecteException("Attente interrompue avant l'appel API", ie);
        }
    }

    /**
     * Recupere toutes les classes accessibles au professeur authentifie.
     *
     * @return les classes rattachees aux niveaux des etablissements accessibles
     * @throws EcoleDirecteException en cas d'erreur lors de l'appel a l'API EcoleDirecte
     */
    private List<Classe> getAllTeacherClasses() throws EcoleDirecteException {
        attendreAvantAppelApi();
        Niveaux niveaux = client.getNiveauxListe();
        if (niveaux == null || niveaux.getEtablissements() == null) {
            return List.of();
        }

        List<Classe> classes = new ArrayList<>();
        for (Etablissement etablissement : niveaux.getEtablissements()) {
            if (etablissement != null && etablissement.getNiveaux() != null) {
                for (Niveau niveau : etablissement.getNiveaux()) {
                    if (niveau != null && niveau.getClasses() != null) {
                        classes.addAll(niveau.getClasses());
                    }
                }
            }
        }
        return classes;
    }

    /**
     * Construit les donnees exportees de tous les eleves d'une classe.
     *
     * @param classe classe dont les eleves doivent etre exportes
     * @return les donnees d'eleves pretes a etre serialisees
     * @throws EcoleDirecteException en cas d'erreur lors des appels a l'API EcoleDirecte
     */
    private List<EleveExporte> buildElevesExportes(Classe classe) throws EcoleDirecteException {
        attendreAvantAppelApi();
        Eleves eleves = client.getClasseEleves(String.valueOf(classe.getId()));
        if (eleves == null || eleves.getEleves() == null) {
            return List.of();
        }

        List<EleveExporte> elevesExportes = new ArrayList<>();
        for (eu.verdelhan.ecoledirecte.v3.classes.Eleve eleve : eleves.getEleves()) {
            if (eleve != null && eleve.getId() != null) {
                String eleveId = String.valueOf(eleve.getId());
                elevesExportes.add(new EleveExporte(
                        buildNomComplet(eleve),
                        buildPeriodesNotes(eleveId),
                        buildVieScolaireExportee(eleveId)
                ));
            }
        }
        return elevesExportes;
    }

    /**
     * Construit le nom complet affiche dans l'export pour un eleve.
     *
     * @param eleve eleve retourne par l'API de classe
     * @return nom complet de l'eleve
     */
    private String buildNomComplet(eu.verdelhan.ecoledirecte.v3.classes.Eleve eleve) {
        return String.join(" ", valeurNonNulle(eleve.getNom()), valeurNonNulle(eleve.getPrenom())).trim();
    }

    /**
     * Construit les periodes de notes exportees pour un eleve.
     *
     * @param eleveId identifiant EcoleDirecte de l'eleve
     * @return periodes de notes pretes a etre serialisees
     * @throws EcoleDirecteException en cas d'erreur lors de l'appel a l'API EcoleDirecte
     */
    private List<PeriodeNotes> buildPeriodesNotes(String eleveId) throws EcoleDirecteException {
        attendreAvantAppelApi();
        Notes notes = client.getEleveNotes(eleveId);
        if (notes == null || notes.getPeriodes() == null) {
            return List.of();
        }

        List<PeriodeNotes> periodesNotes = new ArrayList<>();
        for (Periode periode : notes.getPeriodes()) {
            if (isPeriodeExportable(periode)) {
                EnsembleMatieres ensembleMatieres = periode.getEnsembleMatieres();
                periodesNotes.add(new PeriodeNotes(
                        periode.getPeriode(),
                        buildNotesMatieres(ensembleMatieres),
                        ensembleMatieres.getAppreciationPP()
                ));
            }
        }
        return periodesNotes;
    }

    /**
     * Verifie qu'une periode de notes contient les donnees necessaires pour etre exportee.
     *
     * @param periode periode retournee par l'API de notes
     * @return {@code true} si la periode doit etre exportee, {@code false} sinon
     */
    private boolean isPeriodeExportable(Periode periode) {
        return periode != null
                && periode.getCodePeriode() != null
                && periode.getCodePeriode().length() == EXPORTED_PERIOD_CODE_LENGTH
                && periode.getEnsembleMatieres() != null;
    }

    /**
     * Construit les notes par matiere d'une periode.
     *
     * @param ensembleMatieres ensemble des matieres d'une periode
     * @return notes par matiere pretes a etre serialisees
     */
    private List<NoteMatiere> buildNotesMatieres(EnsembleMatieres ensembleMatieres) {
        if (ensembleMatieres.getDisciplines() == null) {
            return List.of();
        }

        List<NoteMatiere> notesMatieres = new ArrayList<>();
        for (Discipline discipline : ensembleMatieres.getDisciplines()) {
            if (discipline != null) {
                notesMatieres.add(new NoteMatiere(
                        discipline.getMoyenne(),
                        discipline.getMoyenneClasse(),
                        discipline.getMoyenneMin(),
                        discipline.getMoyenneMax(),
                        decodeAppreciations(discipline)
                ));
            }
        }
        return notesMatieres;
    }

    /**
     * Decode les appreciations d'une matiere.
     *
     * @param discipline matiere retournee par l'API de notes
     * @return appreciations decodees en texte lisible
     */
    private List<String> decodeAppreciations(Discipline discipline) {
        if (discipline.getAppreciations() == null) {
            return List.of();
        }
        return discipline.getAppreciations().stream()
                .map(EcoleDirecteUtils::decodeBase64Text)
                .toList();
    }

    /**
     * Construit les donnees de vie scolaire exportees pour un eleve.
     *
     * @param eleveId identifiant EcoleDirecte de l'eleve
     * @return donnees de vie scolaire pretes a etre serialisees
     * @throws EcoleDirecteException en cas d'erreur lors de l'appel a l'API EcoleDirecte
     */
    private VieScolaireExportee buildVieScolaireExportee(String eleveId) throws EcoleDirecteException {
        attendreAvantAppelApi();
        VieScolaire vieScolaire = client.getEleveVieScolaire(eleveId);
        if (vieScolaire == null) {
            return new VieScolaireExportee(List.of(), List.of());
        }
        return new VieScolaireExportee(
                buildAbsencesRetards(vieScolaire),
                buildPunitions(vieScolaire)
        );
    }

    /**
     * Construit les absences et retards exportes d'un eleve.
     *
     * @param vieScolaire vie scolaire retournee par l'API
     * @return absences et retards prets a etre serialises
     */
    private List<AbsenceRetard> buildAbsencesRetards(VieScolaire vieScolaire) {
        if (vieScolaire.getAbsencesRetards() == null) {
            return List.of();
        }

        List<AbsenceRetard> absences = new ArrayList<>();
        for (AbsencesRetard absenceRetard : vieScolaire.getAbsencesRetards()) {
            if (absenceRetard != null) {
                absences.add(new AbsenceRetard(
                        absenceRetard.getDate(),
                        Boolean.TRUE.equals(absenceRetard.getJustifie()),
                        absenceRetard.getMotif()
                ));
            }
        }
        return absences;
    }

    /**
     * Construit les punitions exportees d'un eleve.
     *
     * @param vieScolaire vie scolaire retournee par l'API
     * @return punitions pretes a etre serialisees
     */
    private List<Punition> buildPunitions(VieScolaire vieScolaire) {
        if (vieScolaire.getSanctionsEncouragements() == null) {
            return List.of();
        }

        List<Punition> punitions = new ArrayList<>();
        for (SanctionsEncouragement sanctionEncouragement : vieScolaire.getSanctionsEncouragements()) {
            if (sanctionEncouragement != null && PUNITION_TYPE.equals(sanctionEncouragement.getTypeElement())) {
                punitions.add(new Punition(
                        sanctionEncouragement.getDate(),
                        sanctionEncouragement.getLibelle(),
                        sanctionEncouragement.getMotif()
                ));
            }
        }
        return punitions;
    }

    /**
     * Retourne une chaine vide lorsqu'une valeur textuelle de l'API est absente.
     *
     * @param value valeur retournee par l'API
     * @return la valeur non nulle
     */
    private String valeurNonNulle(String value) {
        return value == null ? "" : value;
    }

    /** Identifiants utilises pour authentifier le client EcoleDirecte. */
    record Identifiants(String identifiant, String motDePasse) {}

    /** Notes exportees pour une matiere d'une periode. */
    record NoteMatiere(String moyenne, String moyenneClasse, String moyenneMin, String moyenneMax,
                        List<String> appreciations) {}

    /** Periode de notes exportee pour un eleve. */
    record PeriodeNotes(String libelle, List<NoteMatiere> notesMatieres, String appreciationProfPrincipal) {}

    /** Absence ou retard exporte pour un eleve. */
    record AbsenceRetard(String date, boolean justifie, String motif) {}

    /** Punition exportee pour un eleve. */
    record Punition(String date, String libelle, String motif) {}

    /** Donnees de vie scolaire exportees pour un eleve. */
    record VieScolaireExportee(List<AbsenceRetard> absencesRetards, List<Punition> punitions) {}

    /** Donnees exportees pour un eleve. */
    record EleveExporte(String nomComplet, List<PeriodeNotes> periodesNotes, VieScolaireExportee vieScolaire) {}

    /** Donnees exportees pour une classe. */
    record ClasseExportee(String libelle, List<EleveExporte> eleves) {}
}
