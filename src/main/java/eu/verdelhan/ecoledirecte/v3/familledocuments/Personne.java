
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import com.google.gson.annotations.Expose;

public record Personne(
    @Expose
    Long id,

    @Expose
    String nom,

    @Expose
    String prenom,

    @Expose
    String type
) {
}
