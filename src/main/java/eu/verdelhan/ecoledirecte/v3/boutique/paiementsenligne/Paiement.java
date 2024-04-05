
package eu.verdelhan.ecoledirecte.v3.boutique.paiementsenligne;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Paiement {

    @Expose
    private Boolean acompte;
    @Expose
    private String code;
    @Expose
    private String detail;
    @Expose
    private String id;
    @Expose
    private String img;
    @Expose
    private String infoComplementaire;
    @Expose
    private String libelle;
    @Expose
    private Long montant;
    @Expose
    private Boolean montantModifiable;
    @Expose
    private Boolean quantiteModifiable;
    @Expose
    private String typePaiement;

}
