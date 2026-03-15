
package eu.verdelhan.ecoledirecte.v3.auth.login;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Groupe(
    @Expose
    List<String> classes,

    @Expose
    String code,

    @Expose
    Long id,

    @Expose
    String libelle
) {
}
