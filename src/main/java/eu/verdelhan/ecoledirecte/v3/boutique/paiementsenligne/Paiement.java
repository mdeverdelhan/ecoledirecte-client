
package eu.verdelhan.ecoledirecte.v3.boutique.paiementsenligne;

import com.google.gson.annotations.Expose;

public record Paiement(
    @Expose
    Boolean acompte,

    @Expose
    String code,

    @Expose
    String detail,

    @Expose
    String id,

    @Expose
    String img,

    @Expose
    String infoComplementaire,

    @Expose
    String libelle,

    @Expose
    Long montant,

    @Expose
    Boolean montantModifiable,

    @Expose
    Boolean quantiteModifiable,

    @Expose
    String typePaiement
) {
}
