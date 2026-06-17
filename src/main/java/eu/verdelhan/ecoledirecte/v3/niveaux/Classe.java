package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Classe {

    @Expose
    private int id;

    @Expose
    private String libelle;

    @Expose
    private String code;

    @Expose
    private int idGroupe;

    @Expose
    private boolean isPP;

    @Expose
    private int estNote;

    @Expose
    private int positionnementLSU;

    @Expose
    private int degre;

    @Expose
    private int idCycleEtab;

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
    private List<ProfPrincipal> tabPP;

    @Expose
    private List<Periode> periodes;
}