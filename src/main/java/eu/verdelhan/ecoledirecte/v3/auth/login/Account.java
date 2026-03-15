
package eu.verdelhan.ecoledirecte.v3.auth.login;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Account(
    @Expose
    String civilite,

    @Expose
    String codeOgec,

    @Expose
    String couleurAgendaEtablissement,

    @Expose
    Boolean dicoEnLigneLeRobert,

    @Expose
    String email,

    @Expose
    Long id,

    @Expose
    Long idLogin,

    @Expose
    String identifiant,

    @Expose
    String lastConnexion,

    @Expose
    String logoEtablissement,

    @Expose
    Boolean main,

    @Expose
    List<Module> modules,

    @Expose
    String nom,

    @Expose
    String nomEtablissement,

    @Expose
    ParametresIndividuels parametresIndividuels,

    @Expose
    String particule,

    @Expose
    String prenom,

    @Expose
    Profile profile,

    @Expose
    String socketToken,

    @Expose
    String typeCompte,

    @Expose
    String uid
) {
}
