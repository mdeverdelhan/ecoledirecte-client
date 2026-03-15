
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public record Parametrage(
    @Expose
    List<ParamAppreciation> appreciations,

    @Expose
    Long longueurMaxAppPP,

    @Expose
    List<ParamMention> mentions,

    @SerializedName("PPModifTout")
    Boolean pPModifTout,

    @SerializedName("PPModifVS")
    Boolean pPModifVS,

    @Expose
    Boolean saisieAppreciationClasse
) {
}
