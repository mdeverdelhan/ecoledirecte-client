
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Matiere(
    @Expose
    Long afc,

    @Expose
    Boolean cdt,

    @Expose
    String codeMatiere,

    @Expose
    String codeSousMatiere,

    @Expose
    Long idElemProg,

    @Expose
    Boolean isFirstOfMatiere,

    @Expose
    Boolean isFirstOfSousMatiere,

    @Expose
    String libelleElementProgramme,

    @Expose
    String libelleMatiere,

    @Expose
    String libelleSousMatiere,

    @Expose
    Long nbElemProgMatiere,

    @Expose
    Long nbElemProgSousMatiere,

    @Expose
    List<Professeur> professeurs,

    @Expose
    String valeur
) {
}
