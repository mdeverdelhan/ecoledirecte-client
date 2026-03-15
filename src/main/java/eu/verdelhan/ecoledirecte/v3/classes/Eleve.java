
package eu.verdelhan.ecoledirecte.v3.classes;


import com.google.gson.annotations.Expose;


public record Eleve(
    @Expose
    Long classeId,

    @Expose
    String classeLibelle,

    @Expose
    String dateEntree,

    @Expose
    String dateNaissance,

    @Expose
    String dateSortie,

    @Expose
    String email,

    @Expose
    Boolean estEnStage,

    @Expose
    Long id,

    @Expose
    String nom,

    @Expose
    String numeroBadge,

    @Expose
    String particule,

    @Expose
    String photo,

    @Expose
    String portable,

    @Expose
    String prenom,

    @Expose
    String regime,

    @Expose
    String sexe
) {
}
