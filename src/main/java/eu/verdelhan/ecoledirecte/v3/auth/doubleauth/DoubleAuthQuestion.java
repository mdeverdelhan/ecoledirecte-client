
package eu.verdelhan.ecoledirecte.v3.auth.doubleauth;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class DoubleAuthQuestion {

    @Expose
    private List<String> propositions;
    @Expose
    private String question;

}
