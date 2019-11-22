package cl.utem.dist.proyecto.vo.serviplott;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParadaVO implements Serializable {

    private final static long serialVersionUID = 5980017604701191681L;

    @JsonProperty("id")
    private Integer id = null;
    @JsonProperty("codigo")
    private String codigo = null;
    @JsonProperty("nombre")
    private String nombre = null;
    @JsonProperty("lat")
    private Float lat = null;
    @JsonProperty("lng")
    private Float lng = null;
    @JsonProperty("comuna")
    private ComunaVO comuna = null;
    @JsonProperty("orientacion")
    private OrientacionVO orientacion = null;
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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public ComunaVO getComuna() {
        return comuna;
    }

    public void setComuna(ComunaVO comuna) {
        this.comuna = comuna;
    }

    public OrientacionVO getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionVO orientacion) {
        this.orientacion = orientacion;
    }

    public List<RecorridoVO> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(List<RecorridoVO> recorridos) {
        this.recorridos = recorridos;
    }

}
