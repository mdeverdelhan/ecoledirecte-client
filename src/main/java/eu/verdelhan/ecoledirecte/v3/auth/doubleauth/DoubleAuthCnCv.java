
package eu.verdelhan.ecoledirecte.v3.auth.doubleauth;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DoubleAuthCnCv {

    @Expose
    private String cn;
    @Expose
    private String cv;

}
