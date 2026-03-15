
package eu.verdelhan.ecoledirecte.v3.contactetablissement;

import java.util.List;
import com.google.gson.annotations.Expose;

public record ContactEtablissement(
    @Expose
    String adresse,

    @Expose
    String contact,

    @Expose
    String email,

    @Expose
    Long id,

    @Expose
    Logo logo,

    @Expose
    String nom,

    @Expose
    List<Object> photos,

    @Expose
    String presentation,

    @Expose
    String site,

    @Expose
    String telephone
) {
}
