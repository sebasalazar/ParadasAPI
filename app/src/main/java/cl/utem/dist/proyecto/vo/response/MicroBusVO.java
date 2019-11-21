package cl.utem.dist.proyecto.vo.response;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

@ApiModel(value = "microbus")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MicroBusVO extends BaseBean {

    private String recorrido = null;
    private String tipo = null;

    public MicroBusVO() {
        this.recorrido = StringUtils.EMPTY;
        this.tipo = StringUtils.EMPTY;
    }

    public MicroBusVO(Micro micro) {
        this.recorrido = micro.getRecorrido();
        this.tipo = micro.getTipo().name();
    }

    @ApiModelProperty(value = "Número de recorrido", required = true, example = "101")
    public String getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(String recorrido) {
        this.recorrido = recorrido;
    }

    @ApiModelProperty(value = "Tipo de recorrido", required = true, example = "CORTO")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
