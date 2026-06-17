package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Matiere {

    @Expose
    private String id;

    @Expose
    private String libelle;

    @Expose
    private String libelleCourt;

    @Expose
    private String code;

    @Expose
    private int coef;

    @Expose
    private String codeSSMatiere;

    @Expose
    private String codeGestion;

    @Expose
    private int avecSousMatiere;

    @Expose
    private int calculSousMatiere;

    @Expose
    private String isEditable;

    @Expose
    private String type;

    @Expose
    private boolean saisieAppreciationSSMat;

    @Expose
    private boolean avecNotation;

    @Expose
    private boolean evaluationLSUN;

    @Expose
    private int idMatiereCycle;
}