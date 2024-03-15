
package eu.verdelhan.ecoledirecte.v3.classes;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Entity {

    @Expose
    private String code;
    @Expose
    private Long id;
    @Expose
    private Boolean isFlexible;
    @Expose
    private String libelle;
    @Expose
    private String type;


}
