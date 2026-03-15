
package eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille;

import com.google.gson.annotations.Expose;

public record CoordonneesFamille(
    @Expose
    String adresseLigne1,

    @Expose
    String adresseLigne2,

    @Expose
    String adresseLigne3,

    @Expose
    String codePostal,

    @Expose
    Conjoint conjoint,

    @Expose
    Responsable responsable,

    @Expose
    Long typeLien,

    @Expose
    String ville
) {
}
