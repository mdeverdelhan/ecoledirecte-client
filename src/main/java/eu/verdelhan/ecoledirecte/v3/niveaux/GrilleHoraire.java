package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class GrilleHoraire {

    @Expose
    private String heure_debut;

    @Expose
    private String heure_fin;
}