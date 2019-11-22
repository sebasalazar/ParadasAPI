package cl.utem.dist.proyecto.vo.serviplott;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComunaVO implements Serializable {

    private final static long serialVersionUID = -2147034263221630747L;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nombre")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
