
package eu.verdelhan.ecoledirecte.v3.auth.login;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class ParametresIndividuels {

    @Expose
    private Boolean accessibiliteVisuelle;
    @Expose
    private Boolean blocActuAccueil;
    @Expose
    private Boolean blocPMAccueil;
    @Expose
    private Boolean checkAuthentificationSecure;
    @Expose
    private Boolean isQrcode;
    @Expose
    private String lsuPoilDansLaMainBorne1;
    @Expose
    private String lsuPoilDansLaMainBorne2;
    @Expose
    private String lsuPoilDansLaMainBorne3;
    @Expose
    private String modeCalculLSU;
    @Expose
    private String nbJoursMaxRenduDevoirCDT;
    @Expose
    private String typeSaisieNotesDefaut;
    @Expose
    private String typeViewCDTDefaut;

}
