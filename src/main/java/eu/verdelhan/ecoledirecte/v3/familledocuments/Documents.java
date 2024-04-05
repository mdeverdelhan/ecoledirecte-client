
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Documents {

    @Expose
    private List<Administratif> administratifs;
    @Expose
    private List<Facture> factures;
    @Expose
    private List<Object> inscriptions;
    @Expose
    private PiecesAVerser listesPiecesAVerser;
    @Expose
    private List<Object> notes;
    @Expose
    private List<Object> viescolaire;

}
