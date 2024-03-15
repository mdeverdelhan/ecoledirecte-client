
package eu.verdelhan.ecoledirecte.v3.login;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Groupe {

    @Expose
    private List<String> classes;
    @Expose
    private String code;
    @Expose
    private Long id;
    @Expose
    private String libelle;

}
