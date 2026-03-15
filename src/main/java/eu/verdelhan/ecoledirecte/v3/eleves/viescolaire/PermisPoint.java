
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import java.util.List;
import com.google.gson.annotations.Expose;

public record PermisPoint(
    @Expose
    String dateDebut,

    @Expose
    String dateFin,

    @Expose
    List<Object> evenements,

    @Expose
    Long idPermis,

    @Expose
    String libellePermis,

    @Expose
    Long totalPoints
) {
}
