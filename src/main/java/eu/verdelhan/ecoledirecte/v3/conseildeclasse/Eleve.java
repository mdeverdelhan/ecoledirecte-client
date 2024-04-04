
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Eleve {

    @Expose
    private Appreciation appreciationCE;
    @Expose
    private Appreciation appreciationCN;
    @Expose
    private Appreciation appreciationPP;
    @Expose
    private Appreciation appreciationVS;
    @Expose
    private Long id;
    @Expose
    private Appreciation mentionDuConseil;
    @Expose
    private String nom;
    @Expose
    private String ordreArrivee;
    @Expose
    private String particule;
    @Expose
    private String photo;
    @Expose
    private String prenom;

}
