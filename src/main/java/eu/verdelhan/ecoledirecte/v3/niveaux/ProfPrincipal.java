package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class ProfPrincipal {

    @Expose
    private String nom;

    @Expose
    private String prenom;

    @Expose
    private int id;

    @Expose
    private String type;
}