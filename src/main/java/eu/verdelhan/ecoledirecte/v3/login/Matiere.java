
package eu.verdelhan.ecoledirecte.v3.login;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Matiere {

    @Expose
    private String code;
    @Expose
    private Long id;
    @Expose
    private String libelle;
    
}
