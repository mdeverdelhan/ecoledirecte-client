
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Periode {

    @Expose
    private Boolean annuel;
    @Expose
    private Boolean cloture;
    @Expose
    private String codePeriode;
    @Expose
    private String dateConseil;
    @Expose
    private String dateDebut;
    @Expose
    private String dateFin;
    @Expose
    private EnsembleMatieres ensembleMatieres;
    @Expose
    private Boolean examenBlanc;
    @Expose
    private String idPeriode;
    @Expose
    private Long moyNbreJoursApresConseil;
    @Expose
    private String periode;

}
