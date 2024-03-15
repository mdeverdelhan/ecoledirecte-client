
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class PermisPoint {

    @Expose
    private String dateDebut;
    @Expose
    private String dateFin;
    @Expose
    private List<Object> evenements;
    @Expose
    private Long idPermis;
    @Expose
    private String libellePermis;
    @Expose
    private Long totalPoints;
}
