
package eu.verdelhan.ecoledirecte.v3.classes;

import java.util.List;

import com.google.gson.annotations.Expose;


public record Eleves(
    @Expose
    List<Eleve> eleves,

    @Expose
    Entity entity
) {
}
