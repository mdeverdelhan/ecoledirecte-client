
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class PiecesAVerser {

    @Expose
    private List<Object> listesPieces;
    @Expose
    private List<Personne> personnes;
    @Expose
    private List<Object> pieces;
    @Expose
    private List<Object> televersements;
}
