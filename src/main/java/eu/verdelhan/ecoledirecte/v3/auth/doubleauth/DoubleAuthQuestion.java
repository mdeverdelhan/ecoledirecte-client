
package eu.verdelhan.ecoledirecte.v3.auth.doubleauth;

import java.util.List;
import com.google.gson.annotations.Expose;

public record DoubleAuthQuestion(
    @Expose
    List<String> propositions,

    @Expose
    String question
) {
}
