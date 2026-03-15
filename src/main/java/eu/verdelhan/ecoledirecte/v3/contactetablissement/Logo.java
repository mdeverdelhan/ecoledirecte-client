
package eu.verdelhan.ecoledirecte.v3.contactetablissement;

import java.util.List;
import com.google.gson.annotations.Expose;

public record Logo(
    @Expose
    List<Object> etatSignatures,

    @Expose
    Long id,

    @Expose
    String libelle,

    @Expose
    Signature signature,

    @Expose
    Boolean signatureDemandee,

    @Expose
    String type
) {
}
