
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Parametrage {

    @Expose
    private Boolean absenceCommentaire;
    @Expose
    private Boolean afficherPermisPoint;
    @Expose
    private Boolean encouragementCommentaire;
    @Expose
    private Boolean encouragementParQui;
    @Expose
    private Boolean encouragementsVisible;
    @Expose
    private Boolean justificationEnLigne;
    @Expose
    private Boolean retardCommentaire;
    @Expose
    private Boolean sanctionCommentaire;
    @Expose
    private Boolean sanctionParQui;
    @Expose
    private Boolean sanctionsVisible;

}
