
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class AppreciationsParametrage {

    @Expose
    private String code;
    @Expose
    private Long id;
    @Expose
    private String libelle;
    @Expose
    private Long nbMaxCaractere;

}
