
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;


public record Professeur(
    @Expose
    Long id,

    @Expose
    String nom
) {
}
