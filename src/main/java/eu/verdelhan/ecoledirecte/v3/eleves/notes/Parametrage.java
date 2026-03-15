
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Parametrage(
    @Expose
    Boolean affichageAppreciation,

    @Expose
    Boolean affichageAppreciationCE,

    @Expose
    Boolean affichageAppreciationCN,

    @Expose
    Boolean affichageAppreciationClasse,

    @Expose
    Boolean affichageAppreciationPeriodeCloturee,

    @Expose
    Boolean affichageAppreciationVS,

    @Expose
    Boolean affichageCompNum,

    @Expose
    Boolean affichageCompetence,

    @Expose
    Boolean affichageEvaluationsComposantes,

    @Expose
    Boolean affichageGraphiquesComposantes,

    @Expose
    Boolean affichageMention,

    @Expose
    Boolean affichageMoyenne,

    @Expose
    Boolean affichageMoyenneDevoir,

    @Expose
    Boolean affichageNote,

    @Expose
    Long affichageOngletCompetence,

    @Expose
    Boolean affichagePositionMatiere,

    @Expose
    Boolean appreciationProfPrinc,

    @Expose
    List<AppreciationsParametrage> appreciationsParametrage,

    @Expose
    Boolean appreciationsProf,

    @Expose
    Boolean coefficientNote,

    @Expose
    Boolean colonneCoefficientMatiere,

    @Expose
    String couleurEval1,

    @Expose
    String couleurEval2,

    @Expose
    String couleurEval3,

    @Expose
    String couleurEval4,

    @Expose
    Boolean dateDevoir,

    @Expose
    Boolean libelleDevoir,

    @Expose
    String libelleEval1,

    @Expose
    String libelleEval2,

    @Expose
    String libelleEval3,

    @Expose
    String libelleEval4,

    @Expose
    String libelleEvalCompNum1,

    @Expose
    String libelleEvalCompNum2,

    @Expose
    String libelleEvalCompNum3,

    @Expose
    List<String> libellesAppreciations,

    @Expose
    String modeCalculGraphiquesComposantes,

    @Expose
    Boolean moyenneClasse,

    @Expose
    Boolean moyenneCoefMatiere,

    @Expose
    Boolean moyenneEleve,

    @Expose
    Boolean moyenneEleveDansMoyenne,

    @Expose
    Boolean moyenneEleveDansNotes,

    @Expose
    Boolean moyenneGenerale,

    @Expose
    Boolean moyenneGraphique,

    @Expose
    Boolean moyenneMax,

    @Expose
    Boolean moyenneMin,

    @Expose
    Boolean moyennePeriodeAnnuelle,

    @Expose
    Boolean moyennePeriodeHorsP,

    @Expose
    Boolean moyennePeriodeReleve,

    @Expose
    Boolean moyenneRang,

    @Expose
    Long moyenneSur,

    @Expose
    Boolean moyenneUniquementPeriodeCloture,

    @Expose
    Boolean moyennesSimulation,

    @Expose
    Boolean noteGrasAudessusMoyenne,

    @Expose
    Boolean noteGrasSousMoyenne,

    @Expose
    Boolean notePeriodeAnnuelle,

    @Expose
    Boolean notePeriodeHorsP,

    @Expose
    Boolean notePeriodeReleve,

    @Expose
    Boolean noteUniquementPeriodeCloture,

    @Expose
    Boolean typeDevoir
) {
}
