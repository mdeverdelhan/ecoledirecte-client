package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class ParamsLSU {

    @Expose
    private String couleurEval1;

    @Expose
    private String couleurEval2;

    @Expose
    private String couleurEval3;

    @Expose
    private String couleurEval4;

    @Expose
    private String libelleEval1;

    @Expose
    private String libelleEval2;

    @Expose
    private String libelleEval3;

    @Expose
    private String libelleEval4;

    @Expose
    private String emojiEval1;

    @Expose
    private String emojiEval2;

    @Expose
    private String emojiEval3;

    @Expose
    private String emojiEval4;

    @Expose
    private int nombreCaracteresMax;

    @Expose
    private int saisieLibreActive;

    @Expose
    private int nbElementsProgrammeImprimes;

    @Expose
    private int nbElementsProgrammeImprimesSousMatiere;

    @Expose
    private String libelleCycle;

    @Expose
    private int numeroCycle;
}