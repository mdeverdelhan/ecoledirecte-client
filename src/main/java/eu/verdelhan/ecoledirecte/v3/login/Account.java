
package eu.verdelhan.ecoledirecte.v3.login;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Account {

    @Expose
    private String civilite;
    @Expose
    private String codeOgec;
    @Expose
    private String couleurAgendaEtablissement;
    @Expose
    private Boolean dicoEnLigneLeRobert;
    @Expose
    private String email;
    @Expose
    private Long id;
    @Expose
    private Long idLogin;
    @Expose
    private String identifiant;
    @Expose
    private String lastConnexion;
    @Expose
    private String logoEtablissement;
    @Expose
    private Boolean main;
    @Expose
    private List<Module> modules;
    @Expose
    private String nom;
    @Expose
    private String nomEtablissement;
    @Expose
    private ParametresIndividuels parametresIndividuels;
    @Expose
    private String particule;
    @Expose
    private String prenom;
    @Expose
    private Profile profile;
    @Expose
    private String socketToken;
    @Expose
    private String typeCompte;
    @Expose
    private String uid;

}
