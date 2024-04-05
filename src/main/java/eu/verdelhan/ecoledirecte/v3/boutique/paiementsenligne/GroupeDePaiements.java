
package eu.verdelhan.ecoledirecte.v3.boutique.paiementsenligne;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class GroupeDePaiements {

    @Expose
    private String libelle;
    @Expose
    private List<Paiement> paiements;

}
