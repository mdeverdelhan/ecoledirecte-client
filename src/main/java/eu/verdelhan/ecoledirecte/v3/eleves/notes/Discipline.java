
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Discipline(
    @Expose
    List<String> appreciations,

    @Expose
    String codeMatiere,

    @Expose
    String codeSousMatiere,

    @Expose
    Long coef,

    @Expose
    String discipline,

    @Expose
    Long effectif,

    @Expose
    Boolean groupeMatiere,

    @Expose
    Long id,

    @Expose
    Long idGroupeMatiere,

    @Expose
    String moyenne,

    @Expose
    String moyenneClasse,

    @Expose
    String moyenneMax,

    @Expose
    String moyenneMin,

    @Expose
    Long option,

    @Expose
    List<Professeur> professeurs,

    @Expose
    Long rang,

    @Expose
    Boolean saisieAppreciationSSMat,

    @Expose
    Boolean sousMatiere
) {
}
