package cl.utem.dist.proyecto.api.v1.impl;

import cl.utem.dist.proyecto.api.v1.DireccionRest;
import cl.utem.dist.proyecto.servicio.GoogleService;
import cl.utem.dist.proyecto.vo.GeoVO;
import cl.utem.dist.proyecto.vo.response.DireccionVO;
import cl.utem.dist.proyecto.vo.response.ErrorVO;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/v1/direcciones"}, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
public class DireccionRestImpl implements DireccionRest, Serializable {

    @Autowired
    private transient GoogleService googleService;

    @Override
    @RequestMapping(value = "/coordenadas", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getDireccion(@RequestParam(name = "direccion") String direccion) {

        if (StringUtils.isEmpty(direccion)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorVO("Se debe especificar una dirección"));
        }

        StringBuilder builder = new StringBuilder(StringUtils.trimToEmpty(direccion));
        if (!StringUtils.containsIgnoreCase(direccion, "Región Metropolitana")) {
            builder.append(", Región Metropolitana");
        }

        if (!StringUtils.containsIgnoreCase(direccion, "Chile")) {
            builder.append(", Chile");
        }

        String direccionRM = builder.toString();
        GeoVO geo = googleService.getAddress(direccionRM);
        if (geo == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorVO("No se ha encontrado la dirección"));
        }

        return ResponseEntity.ok(new DireccionVO(direccionRM, geo));
    }

}
