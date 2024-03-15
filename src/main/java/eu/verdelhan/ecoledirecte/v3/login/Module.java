
package eu.verdelhan.ecoledirecte.v3.login;


import com.google.gson.annotations.Expose;
import lombok.Getter;


@Getter
public class Module {

    @Expose
    private Long badge;
    @Expose
    private String code;
    @Expose
    private Boolean enable;
    @Expose
    private Long ordre;
    @Expose
    private Params params;
    
}
