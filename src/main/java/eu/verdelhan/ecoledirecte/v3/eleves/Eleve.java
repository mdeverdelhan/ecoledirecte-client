
package eu.verdelhan.ecoledirecte.v3.eleves;


import com.google.gson.annotations.Expose;

public record Eleve(
    @Expose
    Long classeEstNote,

    @Expose
    Long classeId,

    @Expose
    String classeLibelle,

    @Expose
    String dateDeNaissance,

    @Expose
    String email,

    @Expose
    Long id,

    @Expose
    Long idEtablissement,

    @Expose
    String mobile,

    @Expose
    String nom,

    @Expose
    String particule,

    @Expose
    String photo,

    @Expose
    String prenom,

    @Expose
    String regime,

    @Expose
    String sexe
) {
}
