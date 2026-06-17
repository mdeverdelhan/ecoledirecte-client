package eu.verdelhan.ecoledirecte.v3.niveaux;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Cycle {

    @Expose
    private int idCycleEtab;

    @Expose
    private ParamsLSU paramsLSU;
}