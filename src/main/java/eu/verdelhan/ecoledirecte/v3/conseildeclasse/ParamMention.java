
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;


public record ParamMention(
    @Expose
    Long id,

    @Expose
    String libelle,

    @Expose
    Long numLigne
) {
}
