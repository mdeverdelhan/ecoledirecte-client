
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import com.google.gson.annotations.Expose;

public record AbsencesRetard(
    @Expose
    String aFaire,

    @Expose
    String commentaire,

    @Expose
    String date,

    @Expose
    String dateDeroulement,

    @Expose
    String displayDate,

    @Expose
    Long id,

    @Expose
    Long idEleve,

    @Expose
    Boolean justifie,

    @Expose
    Boolean justifieEd,

    @Expose
    String libelle,

    @Expose
    String motif,

    @Expose
    String nomEleve,

    @Expose
    String par,

    @Expose
    Long pointsPermis,

    @Expose
    String typeElement,

    @Expose
    String typeJustification
) {
}
