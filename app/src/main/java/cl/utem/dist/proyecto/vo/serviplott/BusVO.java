package cl.utem.dist.proyecto.vo.serviplott;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusVO extends BaseBean {

    private final static long serialVersionUID = 3032260596136902197L;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("color")
    private String color;

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
}
