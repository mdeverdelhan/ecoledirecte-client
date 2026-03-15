
package eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille;

import com.google.gson.annotations.Expose;

public record Csp(
    @Expose
    String code,

    @Expose
    String libelle
) {
}
