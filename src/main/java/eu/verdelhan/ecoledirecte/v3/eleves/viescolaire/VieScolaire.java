
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class VieScolaire {

    @Expose
    private List<AbsencesRetard> absencesRetards;
    @Expose
    private Parametrage parametrage;
    @Expose
    private PermisPoint permisPoint;
    @Expose
    private List<SanctionsEncouragement> sanctionsEncouragements;
}
