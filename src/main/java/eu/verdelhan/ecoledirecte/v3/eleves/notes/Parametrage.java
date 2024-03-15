
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Parametrage {

    @Expose
    private Boolean affichageAppreciation;
    @Expose
    private Boolean affichageAppreciationCE;
    @Expose
    private Boolean affichageAppreciationCN;
    @Expose
    private Boolean affichageAppreciationClasse;
    @Expose
    private Boolean affichageAppreciationPeriodeCloturee;
    @Expose
    private Boolean affichageAppreciationVS;
    @Expose
    private Boolean affichageCompNum;
    @Expose
    private Boolean affichageCompetence;
    @Expose
    private Boolean affichageEvaluationsComposantes;
    @Expose
    private Boolean affichageGraphiquesComposantes;
    @Expose
    private Boolean affichageMention;
    @Expose
    private Boolean affichageMoyenne;
    @Expose
    private Boolean affichageMoyenneDevoir;
    @Expose
    private Boolean affichageNote;
    @Expose
    private Long affichageOngletCompetence;
    @Expose
    private Boolean affichagePositionMatiere;
    @Expose
    private Boolean appreciationProfPrinc;
    @Expose
    private List<AppreciationsParametrage> appreciationsParametrage;
    @Expose
    private Boolean appreciationsProf;
    @Expose
    private Boolean coefficientNote;
    @Expose
    private Boolean colonneCoefficientMatiere;
    @Expose
    private String couleurEval1;
    @Expose
    private String couleurEval2;
    @Expose
    private String couleurEval3;
    @Expose
    private String couleurEval4;
    @Expose
    private Boolean dateDevoir;
    @Expose
    private Boolean libelleDevoir;
    @Expose
    private String libelleEval1;
    @Expose
    private String libelleEval2;
    @Expose
    private String libelleEval3;
    @Expose
    private String libelleEval4;
    @Expose
    private String libelleEvalCompNum1;
    @Expose
    private String libelleEvalCompNum2;
    @Expose
    private String libelleEvalCompNum3;
    @Expose
    private List<String> libellesAppreciations;
    @Expose
    private String modeCalculGraphiquesComposantes;
    @Expose
    private Boolean moyenneClasse;
    @Expose
    private Boolean moyenneCoefMatiere;
    @Expose
    private Boolean moyenneEleve;
    @Expose
    private Boolean moyenneEleveDansMoyenne;
    @Expose
    private Boolean moyenneEleveDansNotes;
    @Expose
    private Boolean moyenneGenerale;
    @Expose
    private Boolean moyenneGraphique;
    @Expose
    private Boolean moyenneMax;
    @Expose
    private Boolean moyenneMin;
    @Expose
    private Boolean moyennePeriodeAnnuelle;
    @Expose
    private Boolean moyennePeriodeHorsP;
    @Expose
    private Boolean moyennePeriodeReleve;
    @Expose
    private Boolean moyenneRang;
    @Expose
    private Long moyenneSur;
    @Expose
    private Boolean moyenneUniquementPeriodeCloture;
    @Expose
    private Boolean moyennesSimulation;
    @Expose
    private Boolean noteGrasAudessusMoyenne;
    @Expose
    private Boolean noteGrasSousMoyenne;
    @Expose
    private Boolean notePeriodeAnnuelle;
    @Expose
    private Boolean notePeriodeHorsP;
    @Expose
    private Boolean notePeriodeReleve;
    @Expose
    private Boolean noteUniquementPeriodeCloture;
    @Expose
    private Boolean typeDevoir;

}
