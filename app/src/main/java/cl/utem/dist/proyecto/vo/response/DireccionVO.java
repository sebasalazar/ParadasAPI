package cl.utem.dist.proyecto.vo.response;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import cl.utem.dist.proyecto.vo.GeoVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

@ApiModel(value = "direccion")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DireccionVO extends BaseBean {

    private String direccion = null;
    private GeoVO geolocation = null;

    public DireccionVO() {
        this.direccion = StringUtils.EMPTY;
        this.geolocation = new GeoVO();
    }

    public DireccionVO(String direccion, GeoVO geo) {
        this.direccion = direccion;
        this.geolocation = geo;
    }

    @ApiModelProperty(value = "Dirección de la ubicación", required = true, example = "Av. José Pedro Alessandri 1242, Ñuñoa, Región Metropolitana, Chile")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @ApiModelProperty(value = "Coordenadas de geolocalización", required = true)
    public GeoVO getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeoVO geolocation) {
        this.geolocation = geolocation;
    }
}
