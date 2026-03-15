
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;


public record EnsembleMatieres(
    @Expose
    String appreciationCE,

    @Expose
    String appreciationPP,

    @Expose
    String appreciationVS,

    @Expose
    String dateCalcul,

    @Expose
    String decisionDuConseil,

    @Expose
    List<Discipline> disciplines,

    @Expose
    List<Object> disciplinesSimulation,

    @Expose
    String effectif,

    @Expose
    String moyenneClasse,

    @Expose
    String moyenneGenerale,

    @Expose
    String moyenneMax,

    @Expose
    String moyenneMin,

    @Expose
    String nomCE,

    @Expose
    String nomPP,

    @Expose
    Long rang
) {
}
