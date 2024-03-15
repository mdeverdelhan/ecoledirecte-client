
package eu.verdelhan.ecoledirecte.v3.classes;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Eleve {

    @Expose
    private Long classeId;
    @Expose
    private String classeLibelle;
    @Expose
    private String dateEntree;
    @Expose
    private String dateNaissance;
    @Expose
    private String dateSortie;
    @Expose
    private String email;
    @Expose
    private Boolean estEnStage;
    @Expose
    private Long id;
    @Expose
    private String nom;
    @Expose
    private String numeroBadge;
    @Expose
    private String particule;
    @Expose
    private String photo;
    @Expose
    private String portable;
    @Expose
    private String prenom;
    @Expose
    private String regime;
    @Expose
    private String sexe;



}
