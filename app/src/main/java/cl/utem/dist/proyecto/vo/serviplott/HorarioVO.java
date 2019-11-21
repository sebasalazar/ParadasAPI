package cl.utem.dist.proyecto.vo.serviplott;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "horario")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HorarioVO implements Serializable {

    private final static long serialVersionUID = 5431052194708575900L;
    @JsonProperty("nombre")
    private String nombre = null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
