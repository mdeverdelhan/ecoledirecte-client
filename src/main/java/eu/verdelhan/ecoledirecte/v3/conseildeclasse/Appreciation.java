
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;


public record Appreciation(
    @Expose
    String code,

    @Expose
    String date,

    @Expose
    String id,

    @Expose
    String libelle,

    @Expose
    String text
) {
}
