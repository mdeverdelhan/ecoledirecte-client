
package eu.verdelhan.ecoledirecte.v3.auth.login;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Profile(
    @Expose
    List<Classe> classes,

    @Expose
    String email,

    @Expose
    List<Groupe> groupes,

    @Expose
    String idEtablissement,

    @Expose
    List<Matiere> matieres,

    @Expose
    String nomEtablissement,

    @Expose
    String photo,

    @Expose
    String telPortable
) {
}
