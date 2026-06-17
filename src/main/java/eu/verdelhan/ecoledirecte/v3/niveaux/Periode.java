package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Periode {

    @Expose
    private int id;

    @Expose
    private String codePeriode;

    @Expose
    private int periode;

    @Expose
    private int sousPeriode;

    @Expose
    private int coef;

    @Expose
    private String libelle;

    @Expose
    private String libelleCourt;

    @Expose
    private String etat;

    @Expose
    private boolean saisieAppreciation;

    @Expose
    private boolean saisieAppreciationClasse;

    @Expose
    private String dateDebut;

    @Expose
    private String dateFin;

    @Expose
    private String dateConseil;

    @Expose
    private String salleConseil;

    @Expose
    private List<Matiere> matieres;
}