
package eu.verdelhan.ecoledirecte.v3.eleves.notes;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public record Notes(
    @Expose
    String foStat,

    /** LSUN = Livret Scolaire Unique (cle: code de periode, ex. A001, valeur: liste des matieres) */
    @SerializedName("LSUN")
    Map<String, List<Matiere>> lSUN,

    @Expose
    List<Note> notes,

    @Expose
    Parametrage parametrage,

    @Expose
    List<Periode> periodes
) {
}
