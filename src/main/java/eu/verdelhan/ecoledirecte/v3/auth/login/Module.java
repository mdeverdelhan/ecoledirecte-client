
package eu.verdelhan.ecoledirecte.v3.auth.login;


import com.google.gson.annotations.Expose;


public record Module(
    @Expose
    Long badge,

    @Expose
    String code,

    @Expose
    Boolean enable,

    @Expose
    Long ordre,

    @Expose
    Params params
) {
}
