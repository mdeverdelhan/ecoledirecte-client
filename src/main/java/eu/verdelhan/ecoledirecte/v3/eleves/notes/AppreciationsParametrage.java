
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;


public record AppreciationsParametrage(
    @Expose
    String code,

    @Expose
    Long id,

    @Expose
    String libelle,

    @Expose
    Long nbMaxCaractere
) {
}
