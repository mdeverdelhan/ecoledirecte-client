
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Mention {

    @Expose
    private Long id;
    @Expose
    private String libelle;
    @Expose
    private Long numLigne;
}
