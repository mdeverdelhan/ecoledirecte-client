
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class AppreciationGenerale {

    @Expose
    private String code;
    @Expose
    private String date;
    @Expose
    private String id;
    @Expose
    private String libelle;
    @Expose
    private String text;

}
