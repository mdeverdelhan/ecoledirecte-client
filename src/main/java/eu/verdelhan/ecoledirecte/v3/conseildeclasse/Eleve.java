
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;


public record Eleve(
    @Expose
    Appreciation appreciationCE,

    @Expose
    Appreciation appreciationCN,

    @Expose
    Appreciation appreciationPP,

    @Expose
    Appreciation appreciationVS,

    @Expose
    Long id,

    @Expose
    Appreciation mentionDuConseil,

    @Expose
    String nom,

    @Expose
    String ordreArrivee,

    @Expose
    String particule,

    @Expose
    String photo,

    @Expose
    String prenom
) {
}
