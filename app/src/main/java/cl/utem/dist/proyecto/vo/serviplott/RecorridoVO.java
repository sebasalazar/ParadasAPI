package cl.utem.dist.proyecto.vo.serviplott;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "recorrido")
public class RecorridoVO extends BaseBean {

    @JsonProperty("bus")
    public BusVO bus;
    @JsonProperty("horario")
    public HorarioVO horario;
    private final static long serialVersionUID = -2422060046200785259L;

    public BusVO getBus() {
        return bus;
    }

    public void setBus(BusVO bus) {
        this.bus = bus;
    }

    public HorarioVO getHorario() {
        return horario;
    }

    public void setHorario(HorarioVO horario) {
        this.horario = horario;
    }

}
