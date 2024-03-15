
package eu.verdelhan.ecoledirecte.v3.eleves.viescolaire;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class AbsencesRetard {

    @Expose
    private String aFaire;
    @Expose
    private String commentaire;
    @Expose
    private String date;
    @Expose
    private String dateDeroulement;
    @Expose
    private String displayDate;
    @Expose
    private Long id;
    @Expose
    private Long idEleve;
    @Expose
    private Boolean justifie;
    @Expose
    private Boolean justifieEd;
    @Expose
    private String libelle;
    @Expose
    private String motif;
    @Expose
    private String nomEleve;
    @Expose
    private String par;
    @Expose
    private Long pointsPermis;
    @Expose
    private String typeElement;
    @Expose
    private String typeJustification;
}
