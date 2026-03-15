
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;


public record ElementsProgramme(
    @Expose
    Long afc,

    @Expose
    Boolean cdt,

    @Expose
    String descriptif,

    @Expose
    Long idCompetence,

    @Expose
    Long idConnaissance,

    @Expose
    Long idElemProg,

    @Expose
    String libelleCompetence,

    @Expose
    String valeur
) {
}
