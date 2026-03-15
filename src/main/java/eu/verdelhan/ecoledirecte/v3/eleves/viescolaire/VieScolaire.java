
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import java.util.List;
import com.google.gson.annotations.Expose;

public record VieScolaire(
    @Expose
    List<AbsencesRetard> absencesRetards,

    @Expose
    Parametrage parametrage,

    @Expose
    PermisPoint permisPoint,

    @Expose
    List<SanctionsEncouragement> sanctionsEncouragements
) {
}
