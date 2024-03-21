
package eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Responsable {

    @Expose
    private String civilite;
    @Expose
    private String codePays;
    @Expose
    private Csp csp;
    @Expose
    private String mailPerso;
    @Expose
    private String mailTravail;
    @Expose
    private String nom;
    @Expose
    private String nomSimple;
    @Expose
    private String prenom;
    @Expose
    private String profession;
    @Expose
    private String societe;
    @Expose
    private String telDomicile;
    @Expose
    private String telMobile;
    @Expose
    private String telTravail;
}
