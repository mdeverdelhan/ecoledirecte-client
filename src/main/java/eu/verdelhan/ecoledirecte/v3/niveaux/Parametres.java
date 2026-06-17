package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Parametres {

    @Expose
    private boolean isVisioEnable;

    @Expose
    private boolean appelAvecEDT;

    @Expose
    private boolean appelAvecGrilleHoraire;

    @Expose
    private List<GrilleHoraire> grille;
}