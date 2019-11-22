package cl.utem.dist.proyecto.vo.serviplott;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DireccionVO extends BaseBean {

    private final static long serialVersionUID = 1774983717436503753L;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("paradas")
    private List<ParadaVO> paradas = null;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<ParadaVO> getParadas() {
        return paradas;
    }

    public void setParadas(List<ParadaVO> paradas) {
        this.paradas = paradas;
    }
}
