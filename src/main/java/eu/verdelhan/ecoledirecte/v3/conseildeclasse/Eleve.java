
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Eleve {

    @Expose
    private AppreciationCE appreciationCE;
    @Expose
    private AppreciationCN appreciationCN;
    @Expose
    private AppreciationPP appreciationPP;
    @Expose
    private AppreciationVS appreciationVS;
    @Expose
    private Long id;
    @Expose
    private MentionDuConseil mentionDuConseil;
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
