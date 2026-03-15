
package eu.verdelhan.ecoledirecte.v3.auth.doubleauth;

import com.google.gson.annotations.Expose;

public record DoubleAuthCnCv(
    @Expose
    String cn,

    @Expose
    String cv
) {
}
