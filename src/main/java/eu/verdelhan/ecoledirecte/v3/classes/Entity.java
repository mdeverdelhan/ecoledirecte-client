
package eu.verdelhan.ecoledirecte.v3.classes;


import com.google.gson.annotations.Expose;


public record Entity(
    @Expose
    String code,

    @Expose
    Long id,

    @Expose
    Boolean isFlexible,

    @Expose
    String libelle,

    @Expose
    String type
) {
}
