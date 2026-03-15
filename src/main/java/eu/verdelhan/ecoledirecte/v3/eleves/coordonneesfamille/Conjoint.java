
package eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille;

import com.google.gson.annotations.Expose;

public record Conjoint(
    @Expose
    String civilite,

    @Expose
    String codePays,

    @Expose
    Csp csp,

    @Expose
    String mailPerso,

    @Expose
    String mailTravail,

    @Expose
    String nom,

    @Expose
    String nomSimple,

    @Expose
    String prenom,

    @Expose
    String profession,

    @Expose
    String societe,

    @Expose
    String telMobile,

    @Expose
    String telTravail
) {
}
