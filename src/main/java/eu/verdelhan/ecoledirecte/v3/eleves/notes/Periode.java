
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;


public record Periode(
    @Expose
    Boolean annuel,

    @Expose
    Boolean cloture,

    @Expose
    String codePeriode,

    @Expose
    String dateConseil,

    @Expose
    String dateDebut,

    @Expose
    String dateFin,

    @Expose
    EnsembleMatieres ensembleMatieres,

    @Expose
    Boolean examenBlanc,

    @Expose
    String idPeriode,

    @Expose
    Long moyNbreJoursApresConseil,

    @Expose
    String periode
) {
}
