
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Discipline {

    @Expose
    private List<String> appreciations;
    @Expose
    private String codeMatiere;
    @Expose
    private String codeSousMatiere;
    @Expose
    private Long coef;
    @Expose
    private String discipline;
    @Expose
    private Long effectif;
    @Expose
    private Boolean groupeMatiere;
    @Expose
    private Long id;
    @Expose
    private Long idGroupeMatiere;
    @Expose
    private String moyenne;
    @Expose
    private String moyenneClasse;
    @Expose
    private String moyenneMax;
    @Expose
    private String moyenneMin;
    @Expose
    private Long option;
    @Expose
    private List<Professeur> professeurs;
    @Expose
    private Long rang;
    @Expose
    private Boolean saisieAppreciationSSMat;
    @Expose
    private Boolean sousMatiere;

}
