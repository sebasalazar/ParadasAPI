package cl.utem.dist.proyecto.vo;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "geo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoVO extends BaseBean {

    private static final long serialVersionUID = 1144563506123851520L;

    private Double latitude = null;
    private Double longitude = null;

    @ApiModelProperty(value = "Coordenada de latitud", required = true, example = "-33.4663272")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @ApiModelProperty(value = "Coordenada de longitud", required = true, example = "-70.6003606")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
