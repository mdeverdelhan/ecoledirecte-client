
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;

import com.google.gson.annotations.Expose;

public record ParamAppreciation(
    @Expose
    String code,

    @Expose
    Long id,

    @Expose
    String libelle,

    @Expose
    Long nbCaracteres
) {
}
