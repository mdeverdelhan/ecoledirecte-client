
package eu.verdelhan.ecoledirecte.v3.auth.login;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Profile {

    @Expose
    private List<Classe> classes;
    @Expose
    private String email;
    @Expose
    private List<Groupe> groupes;
    @Expose
    private String idEtablissement;
    @Expose
    private List<Matiere> matieres;
    @Expose
    private String nomEtablissement;
    @Expose
    private String photo;
    @Expose
    private String telPortable;
    
}
