
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class ParamAppreciation {

    @Expose
    private String code;
    @Expose
    private Long id;
    @Expose
    private String libelle;
    @Expose
    private Long nbCaracteres;
}
