package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Niveau {

    @Expose
    private int id;

    @Expose
    private String code;

    @Expose
    private String libelle;

    @Expose
    private List<Classe> classes;
}