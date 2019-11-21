package cl.utem.dist.proyecto.vo.response;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import cl.utem.dist.proyecto.persistencia.modelo.Parada;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "parada")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParadaVO extends BaseBean {

    private MicroBusVO microbus = null;
    private ParaderoVO paradero = null;

    public ParadaVO() {
        this.microbus = null;
        this.paradero = null;
    }

    public ParadaVO(Parada parada) {
        this.microbus = new MicroBusVO(parada.getMicro());
        this.paradero = new ParaderoVO(parada.getParadero());
    }

    public MicroBusVO getMicrobus() {
        return microbus;
    }

    public void setMicrobus(MicroBusVO microbus) {
        this.microbus = microbus;
    }

    public ParaderoVO getParadero() {
        return paradero;
    }

    public void setParadero(ParaderoVO paradero) {
        this.paradero = paradero;
    }
}
