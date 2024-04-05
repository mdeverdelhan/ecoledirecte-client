
package eu.verdelhan.ecoledirecte.v3.familledocuments;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Personne {

    @Expose
    private Long id;
    @Expose
    private String nom;
    @Expose
    private String prenom;
    @Expose
    private String type;

}
