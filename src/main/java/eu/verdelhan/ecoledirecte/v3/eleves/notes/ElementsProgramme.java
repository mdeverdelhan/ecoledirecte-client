
package eu.verdelhan.ecoledirecte.v3.eleves.notes;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class ElementsProgramme {

    @Expose
    private Long afc;
    @Expose
    private Boolean cdt;
    @Expose
    private String descriptif;
    @Expose
    private Long idCompetence;
    @Expose
    private Long idConnaissance;
    @Expose
    private Long idElemProg;
    @Expose
    private String libelleCompetence;
    @Expose
    private String valeur;

}
