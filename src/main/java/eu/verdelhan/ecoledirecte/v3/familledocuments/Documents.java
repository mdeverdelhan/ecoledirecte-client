
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import java.util.List;
import com.google.gson.annotations.Expose;

public record Documents(
    @Expose
    List<Administratif> administratifs,

    @Expose
    List<Facture> factures,

    @Expose
    List<Object> inscriptions,

    @Expose
    PiecesAVerser listesPiecesAVerser,

    @Expose
    List<Object> notes,

    @Expose
    List<Object> viescolaire
) {
}
