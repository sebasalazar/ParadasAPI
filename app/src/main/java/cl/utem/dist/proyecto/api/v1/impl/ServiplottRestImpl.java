package cl.utem.dist.proyecto.api.v1.impl;

import cl.utem.dist.proyecto.api.v1.ServiplottRest;
import cl.utem.dist.proyecto.persistencia.administrador.MicroAdministrador;
import cl.utem.dist.proyecto.persistencia.administrador.ParaderoAdministrador;
import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import cl.utem.dist.proyecto.servicio.GoogleService;
import cl.utem.dist.proyecto.servicio.ServiPlott;
import cl.utem.dist.proyecto.vo.GeoVO;
import cl.utem.dist.proyecto.vo.response.ResponseVO;
import cl.utem.dist.proyecto.vo.serviplott.BusVO;
import cl.utem.dist.proyecto.vo.serviplott.ParadaVO;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/v1/serviplott"}, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
public class ServiplottRestImpl implements ServiplottRest, Serializable {

    private static final long serialVersionUID = 7879998356318138368L;

    @Autowired
    private transient GoogleService googleService;
    @Autowired
    private transient ServiPlott serviPlott;
    @Autowired
    private transient MicroAdministrador microAdministrador;
    @Autowired
    private transient ParaderoAdministrador paraderoAdministrador;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiplottRestImpl.class);

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity update() {

        List<BusVO> buses = serviPlott.getBuses();
        if (CollectionUtils.isNotEmpty(buses)) {
            for (BusVO busvo : buses) {
                String numeroMicro = busvo.getNumero();
                Micro micro = microAdministrador.crear(numeroMicro);
                LOGGER.info("Cargando micro: '{}'", micro);
            }
        }

        List<ParadaVO> paraderos = serviPlott.getParaderos();
        if (CollectionUtils.isNotEmpty(paraderos)) {
            for (ParadaVO paradavo : paraderos) {
                String codigo = StringUtils.upperCase(StringUtils.trimToEmpty(paradavo.getCodigo()));
                Paradero paradero = paraderoAdministrador.getParadero(codigo);
                if (paradero == null) {
                    String direccion = String.format("%s, %s", paradavo.getNombre(), paradavo.getComuna().getNombre());
                    GeoVO geo = googleService.getAddress(direccion);
                    if (geo != null) {
                        paradero = new Paradero();
                        paradero.setCodigo(codigo);
                        paradero.setDireccion(direccion);
                        paradero.setLatitud(geo.getLatitude());
                        paradero.setLongitud(geo.getLongitude());
                        Paradero paraderoGuardado = paraderoAdministrador.guardar(paradero);
                        LOGGER.info("Cargando Paradero: '{}'", paraderoGuardado);
                    }
                }
            }
        }

        return ResponseEntity.ok(new ResponseVO("Datos actualizados"));
    }

}
