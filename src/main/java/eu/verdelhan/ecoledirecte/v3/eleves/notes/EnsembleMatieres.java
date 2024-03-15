
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class EnsembleMatieres {

    @Expose
    private String appreciationCE;
    @Expose
    private String appreciationPP;
    @Expose
    private String appreciationVS;
    @Expose
    private String dateCalcul;
    @Expose
    private String decisionDuConseil;
    @Expose
    private List<Discipline> disciplines;
    @Expose
    private List<Object> disciplinesSimulation;
    @Expose
    private String effectif;
    @Expose
    private String moyenneClasse;
    @Expose
    private String moyenneGenerale;
    @Expose
    private String moyenneMax;
    @Expose
    private String moyenneMin;
    @Expose
    private String nomCE;
    @Expose
    private String nomPP;
    @Expose
    private Long rang;

}
