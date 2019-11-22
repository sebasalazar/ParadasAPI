package cl.utem.dist.proyecto.vo.serviplott;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orientacion",
    "vertices"
})
public class OrientacionVO implements Serializable {

    private final static long serialVersionUID = 962478563496851299L;

    @JsonProperty("orientacion")
    private String orientacion;
    @JsonProperty("vertices")
    private String vertices;

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getVertices() {
        return vertices;
    }

    public void setVertices(String vertices) {
        this.vertices = vertices;
    }

}
