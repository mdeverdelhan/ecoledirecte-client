
package eu.verdelhan.ecoledirecte.v3.eleves.coordonneesfamille;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class CoordonneesFamille {

    @Expose
    private String adresseLigne1;
    @Expose
    private String adresseLigne2;
    @Expose
    private String adresseLigne3;
    @Expose
    private String codePostal;
    @Expose
    private Conjoint conjoint;
    @Expose
    private Responsable responsable;
    @Expose
    private Long typeLien;
    @Expose
    private String ville;
}
