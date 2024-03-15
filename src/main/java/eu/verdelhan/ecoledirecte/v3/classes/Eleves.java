
package eu.verdelhan.ecoledirecte.v3.classes;

import java.util.List;

import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Eleves {

    @Expose
    private List<Eleve> eleves;
    @Expose
    private Entity entity;


}
