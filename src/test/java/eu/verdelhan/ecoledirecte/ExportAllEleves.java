package eu.verdelhan.ecoledirecte;

import eu.verdelhan.ecoledirecte.exceptions.EcoleDirecteException;
import eu.verdelhan.ecoledirecte.v3.auth.login.Account;
import eu.verdelhan.ecoledirecte.v3.classes.Eleves;
import eu.verdelhan.ecoledirecte.v3.eleves.notes.Discipline;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.AbsencesRetard;
import eu.verdelhan.ecoledirecte.v3.eleves.viescolaire.SanctionsEncouragement;
import eu.verdelhan.ecoledirecte.v3.niveaux.Classe;
import eu.verdelhan.ecoledirecte.v3.niveaux.Niveaux;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExportAllEleves {



    public static void main() throws Exception {

        EcoleDirecteClient client = new EcoleDirecteClient("https://api.ecoledirecte.com/v3");
        client.authenticate("x", "y");

        List<Classe> classes = getAllProfClasses(client);

        List<NotedClasse> notedClasses = new ArrayList<>();

        for (Classe c : classes) {

            Eleves eleves = client.getClasseEleves(c.getId() + "");
            List<NotedEleve> notedEleves = new ArrayList<>();
            for (eu.verdelhan.ecoledirecte.v3.classes.Eleve eleve : eleves.getEleves()) {
                NotedEleve notedEleve = new NotedEleve(
                        eleve.getNom() + " " + eleve.getPrenom(),
                        buildNotedPeriodes(eleve.getId() + "", client),
                        buildVs(eleve.getId() + "", client)
                );
                notedEleves.add(notedEleve);
            }

            notedClasses.add(new NotedClasse(
                    c.getLibelle(),
                    notedEleves
            ));

        }


        writeToJson(notedClasses);

    }

    private static void writeToJson(List<NotedClasse> notedClasses) throws IOException {
        // TODO
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        mapper.writeValue(
//                Path.of("noted-classes.json").toFile(),
//                notedClasses
//        );
    }

    @NotNull
    private static List<Classe> getAllProfClasses(EcoleDirecteClient client) throws EcoleDirecteException {
        Niveaux nivs = client.getNiveauxListe();
        List<Classe> classes = nivs.getEtablissements().stream().flatMap(e -> e.getNiveaux().stream())
                .flatMap(n -> n.getClasses().stream())
                        .toList();
        return classes;
    }

    private static List<NotedPeriode> buildNotedPeriodes(String eleveId, EcoleDirecteClient client) throws EcoleDirecteException {
        var notes = client.getEleveNotes(eleveId);

        List<NotedPeriode> notedPers = new ArrayList<>();
        for (eu.verdelhan.ecoledirecte.v3.eleves.notes.Periode p : notes.getPeriodes()) {
            String codePeriode = p.getCodePeriode();
            if (codePeriode.length() == 4) {

                List<NotedDisc> notedDiscs = new ArrayList<>();
                for (Discipline d : p.getEnsembleMatieres().getDisciplines()) {
                    NotedDisc notedDisc = new NotedDisc(
                            d.getMoyenne(),
                            d.getMoyenneClasse(),
                            d.getMoyenneMin(),
                            d.getMoyenneMax(),
                            d.getAppreciations().stream().map(EcoleDirecteUtils::decodeBase64Text).toList()
                    );
                    notedDiscs.add(notedDisc);
                }
                NotedPeriode notedPeriode = new NotedPeriode(
                        p.getPeriode(),
                        notedDiscs,
                        p.getEnsembleMatieres().getAppreciationPP()
                );
                notedPers.add(notedPeriode);
            }
        }

        return notedPers;
    }

    private static Scol buildVs(String eleveId, EcoleDirecteClient client) throws EcoleDirecteException {
        var vs = client.getEleveVieScolaire(eleveId);

        List<Abs> absences = new ArrayList<>();
        for (AbsencesRetard ar : vs.getAbsencesRetards()) {
            absences.add(new Abs(
                    ar.getDate(),
                    ar.getJustifie(),
                    ar.getMotif()
            ));
        }

        List<Pun> puns = new ArrayList<>();
        if (vs.getSanctionsEncouragements() != null) {
            for (SanctionsEncouragement se : vs.getSanctionsEncouragements()) {
                if ("Punition".equals(se.getTypeElement())){
                    puns.add(new Pun(
                            se.getDate(),
                            se.getLibelle(),
                            se.getMotif()
                    ));
                }
            }
        }

        return new Scol(absences, puns);
    }

    record NotedDisc(String moy, String moyClasse, String moyMin, String moyMax, List<String> apprs) {};
    record NotedPeriode(String lbl, List<NotedDisc> discs, String appr) {};

    record Abs(String date, boolean justifie, String motif) {};
    record Pun(String date, String lbl, String motif) {};
    record Scol(List<Abs> absences, List<Pun> punitions) {};

    record NotedEleve(String nom, List<NotedPeriode> notedPeriodes, Scol scol) {};

    record NotedClasse(String lbl, List<NotedEleve> eleves) {};
}
