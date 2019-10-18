package cl.utem.dist.proyecto.vo.serviplott;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParadaVO implements Serializable {

    private final static long serialVersionUID = 5980017604701191681L;

    private Integer id;
@JsonProperty("codigo")
private String codigo;
@JsonProperty("nombre")
private String nombre;
@JsonProperty("lat")
private Float lat;
@JsonProperty("lng")
private Float lng;
@JsonProperty("comuna")
private ComunaVO comuna;
@JsonProperty("orientacion")
private OrientacionVO orientacion;
@JsonProperty("recorridos")
private List<RecorridoVO> recorridos = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ComunaVO getComuna() {
        return comuna;
    }

    public void setComuna(ComunaVO comuna) {
        this.comuna = comuna;
    }
}
