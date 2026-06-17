package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class ClasseSimple {

    @Expose
    private int id;

    @Expose
    private String code;

    @Expose
    private String libelle;
}