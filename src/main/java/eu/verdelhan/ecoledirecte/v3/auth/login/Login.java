
package eu.verdelhan.ecoledirecte.v3.auth.login;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Login(
    @Expose
    List<Account> accounts,

    @Expose
    Boolean changementMDP
) {
}
