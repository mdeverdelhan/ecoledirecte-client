
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;


@Getter
public class Notes {

    @Expose
    private String foStat;
    /** LSUN = Livret Scolaire Unique (cle: code de periode, ex. A001, valeur: liste des matieres) */
    @SerializedName("LSUN")
    private Map<String, List<Matiere>> lSUN;
    @Expose
    private List<Note> notes;
    @Expose
    private Parametrage parametrage;
    @Expose
    private List<Periode> periodes;

}
