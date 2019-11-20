package cl.utem.dist.proyecto.vo.response;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

@ApiModel(value = "respuesta")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO extends BaseBean {

    private static final long serialVersionUID = 6314335557508817920L;

    private boolean ok = true;
    private String message = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date = null;

    public ResponseVO() {
        this.ok = true;
        this.message = "Proceso Exitoso";
        this.date = new Date();
    }

    public ResponseVO(String message) {
        this.ok = true;
        this.message = StringUtils.trimToEmpty(message);
        this.date = new Date();
    }

    @ApiModelProperty(value = "Estado del objeto", required = true, allowableValues = "true, false", example = "true")
    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @ApiModelProperty(value = "Mensaje de la respuesta", required = true, example = "Solicitud procesada exitosamente")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty(value = "Fecha del message", required = true, example = "2018-11-21 13:21:52")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
