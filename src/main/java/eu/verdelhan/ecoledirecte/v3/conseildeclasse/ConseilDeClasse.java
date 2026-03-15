
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;

import java.util.List;

import com.google.gson.annotations.Expose;


public record ConseilDeClasse(
    @Expose
    Appreciation appreciationGenerale,

    @Expose
    List<Eleve> eleves,

    @Expose
    Parametrage parametrage
) {
}
