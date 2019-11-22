package cl.utem.dist.proyecto.api.v1;

import cl.utem.dist.proyecto.vo.response.ErrorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "/api", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
public interface HeartBeatRest {

    @ApiOperation(value = "HeartBeat para determinar si el servicio está arriba o no lo está.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "OK.", response = Void.class),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity heartbeat();
}
