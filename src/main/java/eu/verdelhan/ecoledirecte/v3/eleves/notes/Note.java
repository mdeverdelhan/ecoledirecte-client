
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Note(
    @Expose
    String codeMatiere,

    @Expose
    String codePeriode,

    @Expose
    String codeSousMatiere,

    @Expose
    String coef,

    @Expose
    String commentaire,

    @Expose
    String date,

    @Expose
    String dateSaisie,

    @Expose
    String devoir,

    @Expose
    List<ElementsProgramme> elementsProgramme,

    @Expose
    Boolean enLettre,

    @Expose
    Long id,

    @Expose
    String libelleMatiere,

    @Expose
    String maxClasse,

    @Expose
    String minClasse,

    @Expose
    String moyenneClasse,

    @Expose
    Boolean nonSignificatif,

    @Expose
    String noteSur,

    @Expose
    String typeDevoir,

    @Expose
    String uncCorrige,

    @Expose
    String uncSujet,

    @Expose
    String valeur,

    @Expose
    Boolean valeurisee
) {
}
