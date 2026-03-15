
package eu.verdelhan.ecoledirecte.v3.auth.login;


import com.google.gson.annotations.Expose;


public record Classe(
    @Expose
    String code,

    @Expose
    Long id,

    @Expose
    Long idGroupe,

    @Expose
    String libelle
) {
}
