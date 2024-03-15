
package eu.verdelhan.ecoledirecte.v3.login;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Login {

    @Expose
    private List<Account> accounts;
    @Expose
    private Boolean changementMDP;

}
