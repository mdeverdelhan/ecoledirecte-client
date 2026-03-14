
package eu.verdelhan.ecoledirecte;


import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@ToString
public class EcoleDirecteApiResponse<T> {

    @Expose
    private Long code;
    @Expose
    private String host;
    @Expose
    private T data;
    @Setter(AccessLevel.PACKAGE)
    private JsonElement rawData;
    @Expose
    private String message;
    @Expose
    private String token;

}
