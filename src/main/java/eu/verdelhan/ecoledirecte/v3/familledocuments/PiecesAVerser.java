
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import java.util.List;
import com.google.gson.annotations.Expose;

public record PiecesAVerser(
    @Expose
    List<Object> listesPieces,

    @Expose
    List<Personne> personnes,

    @Expose
    List<Object> pieces,

    @Expose
    List<Object> televersements
) {
}
