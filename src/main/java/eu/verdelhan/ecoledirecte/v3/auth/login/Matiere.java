
package eu.verdelhan.ecoledirecte.v3.auth.login;


import com.google.gson.annotations.Expose;


public record Matiere(
    @Expose
    String code,

    @Expose
    Long id,

    @Expose
    String libelle
) {
}
