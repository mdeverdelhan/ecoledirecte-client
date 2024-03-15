
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Matiere {

    @Expose
    private Long afc;
    @Expose
    private Boolean cdt;
    @Expose
    private String codeMatiere;
    @Expose
    private String codeSousMatiere;
    @Expose
    private Long idElemProg;
    @Expose
    private Boolean isFirstOfMatiere;
    @Expose
    private Boolean isFirstOfSousMatiere;
    @Expose
    private String libelleElementProgramme;
    @Expose
    private String libelleMatiere;
    @Expose
    private String libelleSousMatiere;
    @Expose
    private Long nbElemProgMatiere;
    @Expose
    private Long nbElemProgSousMatiere;
    @Expose
    private List<Professeur> professeurs;
    @Expose
    private String valeur;


}
