package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Niveaux {

    @Expose
    private List<Groupe> groupes;

    @Expose
    private List<Groupe> autresGroupes;

    @Expose
    private List<Etablissement> etablissements;
}