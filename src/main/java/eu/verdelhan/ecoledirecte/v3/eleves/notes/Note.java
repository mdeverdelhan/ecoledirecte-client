
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Note {

    @Expose
    private String codeMatiere;
    @Expose
    private String codePeriode;
    @Expose
    private String codeSousMatiere;
    @Expose
    private String coef;
    @Expose
    private String commentaire;
    @Expose
    private String date;
    @Expose
    private String dateSaisie;
    @Expose
    private String devoir;
    @Expose
    private List<ElementsProgramme> elementsProgramme;
    @Expose
    private Boolean enLettre;
    @Expose
    private Long id;
    @Expose
    private String libelleMatiere;
    @Expose
    private String maxClasse;
    @Expose
    private String minClasse;
    @Expose
    private String moyenneClasse;
    @Expose
    private Boolean nonSignificatif;
    @Expose
    private String noteSur;
    @Expose
    private String typeDevoir;
    @Expose
    private String uncCorrige;
    @Expose
    private String uncSujet;
    @Expose
    private String valeur;
    @Expose
    private Boolean valeurisee;

}
