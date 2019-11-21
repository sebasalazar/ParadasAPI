package cl.utem.dist.proyecto.vo.response;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;

@ApiModel(value = "paradero")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParaderoVO extends BaseBean {

    private String codigo = null;
    private String direccion = null;
    private Double latitud = 0.0;
    private Double longitud = 0.0;

    public ParaderoVO() {
        this.codigo = StringUtils.EMPTY;
        this.direccion = StringUtils.EMPTY;
        this.latitud = 0.0;
        this.longitud = 0.0;
    }

    public ParaderoVO(Paradero paradero) {
        this.codigo = paradero.getCodigo();
        this.direccion = paradero.getDireccion();
        this.latitud = paradero.getLatitud();
        this.longitud = paradero.getLongitud();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
