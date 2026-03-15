
package eu.verdelhan.ecoledirecte.v3.auth.login;


import com.google.gson.annotations.Expose;


public record Params(
    @Expose
    String numeroBadge
) {
}
