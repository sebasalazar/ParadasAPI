package cl.utem.dist.proyecto.vo;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;

public class GeoVO extends BaseBean {

    private static final long serialVersionUID = 1144563506123851520L;

    private Double latitude = null;
    private Double longitude = null;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
