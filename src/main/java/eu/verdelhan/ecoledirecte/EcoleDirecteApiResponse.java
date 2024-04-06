
package eu.verdelhan.ecoledirecte;


import com.google.gson.annotations.Expose;
import lombok.Getter;
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
    @Expose
    private String message;
    @Expose
    private String token;

}
