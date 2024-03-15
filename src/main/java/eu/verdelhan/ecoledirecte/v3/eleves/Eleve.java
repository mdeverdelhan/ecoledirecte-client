
package eu.verdelhan.ecoledirecte.v3.eleves;


import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Eleve {

    @Expose
    private Long classeEstNote;
    @Expose
    private Long classeId;
    @Expose
    private String classeLibelle;
    @Expose
    private String dateDeNaissance;
    @Expose
    private String email;
    @Expose
    private Long id;
    @Expose
    private Long idEtablissement;
    @Expose
    private String mobile;
    @Expose
    private String nom;
    @Expose
    private String particule;
    @Expose
    private String photo;
    @Expose
    private String prenom;
    @Expose
    private String regime;
    @Expose
    private String sexe;

}
