
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import com.google.gson.annotations.Expose;

public record Parametrage(
    @Expose
    Boolean absenceCommentaire,

    @Expose
    Boolean afficherPermisPoint,

    @Expose
    Boolean encouragementCommentaire,

    @Expose
    Boolean encouragementParQui,

    @Expose
    Boolean encouragementsVisible,

    @Expose
    Boolean justificationEnLigne,

    @Expose
    Boolean retardCommentaire,

    @Expose
    Boolean sanctionCommentaire,

    @Expose
    Boolean sanctionParQui,

    @Expose
    Boolean sanctionsVisible
) {
}
