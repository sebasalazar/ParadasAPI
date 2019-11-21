package cl.utem.dist.proyecto.vo.serviplott;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParadaBusVO extends BaseBean {

    private final static long serialVersionUID = 3204225892737375182L;
    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("numero")
    private String numero = null;
    @JsonProperty("color")
    private String color = null;
    @JsonProperty("direccion")
    private List<DireccionVO> direccion = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<DireccionVO> getDireccion() {
        return direccion;
    }

    public void setDireccion(List<DireccionVO> direccion) {
        this.direccion = direccion;
    }
}
