
package eu.verdelhan.ecoledirecte.v3.conseildeclasse;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class ConseilDeClasse {

    @Expose
    private Appreciation appreciationGenerale;
    @Expose
    private List<Eleve> eleves;
    @Expose
    private Parametrage parametrage;
}
