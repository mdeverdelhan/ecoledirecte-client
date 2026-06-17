package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Groupe {

    @Expose
    private int id;

    @Expose
    private String libelle;

    @Expose
    private String code;

    @Expose
    private int codeRectorat;

    @Expose
    private boolean isSansEleve;

    @Expose
    private int etabId;

    @Expose
    private int degre;

    @Expose
    private boolean isFlexible;

    @Expose
    private int pcpNbPeriode;

    @Expose
    private int pcpMoyAnnuelle;

    @Expose
    private int pcpMoyGenAnnee;

    @Expose
    private int pcpMoyPeriode;

    @Expose
    private int pcpMoyMatiere;

    @Expose
    private int estNote;

    @Expose
    private int positionnementLSU;

    @Expose
    private int idCycleEtab;

    @Expose
    private List<Periode> periodes;

    @Expose
    private List<ClasseSimple> classes;
}