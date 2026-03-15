
package eu.verdelhan.ecoledirecte.v3.boutique.paiementsenligne;

import java.util.List;
import com.google.gson.annotations.Expose;

public record GroupeDePaiements(
    @Expose
    String libelle,

    @Expose
    List<Paiement> paiements
) {
}
