package cl.utem.dist.proyecto.api.v1;

import cl.utem.dist.proyecto.vo.response.ErrorVO;
import cl.utem.dist.proyecto.vo.response.MicroBusVO;
import cl.utem.dist.proyecto.vo.response.ParadaVO;
import cl.utem.dist.proyecto.vo.response.ParaderoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "/api", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
public interface RecorridoRest {

    @ApiOperation(value = "Obtención de Bus.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Microbus encontrado.", response = MicroBusVO.class),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "Datos no encontrados.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getBus(@ApiParam(value = "Número de recorrido del bus", required = true) final String numero,
            @ApiParam(value = "Tipo de Recorrido", required = true) final String tipo);

    @ApiOperation(value = "Obtención de Buses.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listado con los microbuses existentes.", response = MicroBusVO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "Datos no encontrados.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getBuses();

    @ApiOperation(value = "Obtención de tipos de recorridos.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listado de recorridos.", response = String.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getTiposRecorridos();

    @ApiOperation(value = "Obtención de un Paradero.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Paradero encontrado.", response = ParaderoVO.class),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "Datos no encontrados.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getParadero(@ApiParam(value = "Código de un paradero", required = true) final String codigo);

    @ApiOperation(value = "Obtención de paraderos.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listado de paraderos", response = ParaderoVO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "Datos no encontrados.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getParaderos();

    @ApiOperation(value = "Listado de Paradas asociadas a un bus.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Paradas encontradas.", response = ParadaVO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "Datos no encontrados.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getParadas(@ApiParam(value = "Número de microbus", required = true) final String numeroBus);

    @ApiOperation(value = "Listado de Paradas asociadas a un bus.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Paradas encontradas.", response = ParadaVO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "La petición es inválida.", response = ErrorVO.class),
        @ApiResponse(code = 401, message = "Las credenciales del usuario no son válidas.", response = ErrorVO.class),
        @ApiResponse(code = 403, message = "El acceso está prohibido.", response = ErrorVO.class),
        @ApiResponse(code = 404, message = "Datos no encontrados.", response = ErrorVO.class),
        @ApiResponse(code = 412, message = "Ocurrió un error de validación", response = ErrorVO.class),
        @ApiResponse(code = 500, message = "Error interno del servidor.", response = ErrorVO.class)
    })
    public ResponseEntity getParadasByMicroBus(@ApiParam(value = "Número de recorrido del bus", required = true) final String numero,
            @ApiParam(value = "Tipo de Recorrido", required = true) final String tipo);
}
