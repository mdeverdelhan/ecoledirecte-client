
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Administratif {

    @Expose
    private String date;
    @Expose
    private List<Object> etatSignatures;
    @Expose
    private Long id;
    @Expose
    private String libelle;
    @Expose
    private Signature signature;
    @Expose
    private Boolean signatureDemandee;
    @Expose
    private String type;

}
