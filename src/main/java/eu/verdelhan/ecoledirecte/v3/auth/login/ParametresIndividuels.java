
package eu.verdelhan.ecoledirecte.v3.auth.login;


import com.google.gson.annotations.Expose;


public record ParametresIndividuels(
    @Expose
    Boolean accessibiliteVisuelle,

    @Expose
    Boolean blocActuAccueil,

    @Expose
    Boolean blocPMAccueil,

    @Expose
    Boolean checkAuthentificationSecure,

    @Expose
    Boolean isQrcode,

    @Expose
    String lsuPoilDansLaMainBorne1,

    @Expose
    String lsuPoilDansLaMainBorne2,

    @Expose
    String lsuPoilDansLaMainBorne3,

    @Expose
    String modeCalculLSU,

    @Expose
    String nbJoursMaxRenduDevoirCDT,

    @Expose
    String typeSaisieNotesDefaut,

    @Expose
    String typeViewCDTDefaut
) {
}
