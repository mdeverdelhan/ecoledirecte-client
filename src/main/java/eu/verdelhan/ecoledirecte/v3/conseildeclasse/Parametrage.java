
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;


@Getter
public class Parametrage {

    @Expose
    private List<Appreciation> appreciations;
    @Expose
    private Long longueurMaxAppPP;
    @Expose
    private List<Mention> mentions;
    @SerializedName("PPModifTout")
    private Boolean pPModifTout;
    @SerializedName("PPModifVS")
    private Boolean pPModifVS;
    @Expose
    private Boolean saisieAppreciationClasse;
}
