
package eu.verdelhan.ecoledirecte.v3.contactetablissement;

import java.util.List;
import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class ContactEtablissement {

    @Expose
    private String adresse;
    @Expose
    private String contact;
    @Expose
    private String email;
    @Expose
    private Long id;
    @Expose
    private Logo logo;
    @Expose
    private String nom;
    @Expose
    private List<Object> photos;
    @Expose
    private String presentation;
    @Expose
    private String site;
    @Expose
    private String telephone;
}
