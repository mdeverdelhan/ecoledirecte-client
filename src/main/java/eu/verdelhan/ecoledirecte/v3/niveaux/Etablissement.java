package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Etablissement {

    @Expose
    private int id;

    @Expose
    private String code;

    @Expose
    private String libelle;

    @Expose
    private String rne;

    @Expose
    private int degre;

    @Expose
    private boolean isTypeDevoirObligatoire;

    @Expose
    private boolean isCoefModifiable;

    @Expose
    private boolean isBorneModifiable;

    @Expose
    private int borneMin;

    @Expose
    private int borneMax;

    @Expose
    private int moyenneSur;

    @Expose
    private boolean saisieLettre;

    @Expose
    private List<Niveau> niveaux;

    @Expose
    private List<Cycle> cycles;

    @Expose
    private Parametres parametres;
}