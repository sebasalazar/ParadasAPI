package cl.utem.dist.proyecto.api.v1.impl;

import cl.utem.dist.proyecto.api.v1.ServiplottRest;
import cl.utem.dist.proyecto.persistencia.administrador.MicroAdministrador;
import cl.utem.dist.proyecto.persistencia.administrador.ParadaAdministrador;
import cl.utem.dist.proyecto.persistencia.administrador.ParaderoAdministrador;
import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.Parada;
import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import cl.utem.dist.proyecto.servicio.GoogleService;
import cl.utem.dist.proyecto.servicio.ServiPlott;
import cl.utem.dist.proyecto.vo.GeoVO;
import cl.utem.dist.proyecto.vo.response.ErrorVO;
import cl.utem.dist.proyecto.vo.response.ResponseVO;
import cl.utem.dist.proyecto.vo.serviplott.BusVO;
import cl.utem.dist.proyecto.vo.serviplott.DireccionVO;
import cl.utem.dist.proyecto.vo.serviplott.ParadaBusVO;
import cl.utem.dist.proyecto.vo.serviplott.ParadaVO;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/v1/serviplott"}, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
public class ServiplottRestImpl implements ServiplottRest, Serializable {

    private static final long serialVersionUID = 7879998356318138368L;

    @Value("${API_KEY_SEBA}")
    private String apiKeySeba;

    @Autowired
    private transient GoogleService googleService;
    @Autowired
    private transient ServiPlott serviPlott;
    @Autowired
    private transient MicroAdministrador microAdministrador;
    @Autowired
    private transient ParaderoAdministrador paraderoAdministrador;
    @Autowired
    private transient ParadaAdministrador paradaAdministrador;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiplottRestImpl.class);

    @Override
    @RequestMapping(value = "/update", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity update(@RequestHeader("X-API-KEY") String apiKey) {

        // Se requiere Password
        if (!StringUtils.equals(apiKey, apiKeySeba)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorVO("Se requieren credenciales para acceder a este método"));
        }

        List<ParadaVO> paraderos = serviPlott.getParaderos();
        if (CollectionUtils.isNotEmpty(paraderos)) {
            for (ParadaVO paradavo : paraderos) {
                String codigo = StringUtils.upperCase(StringUtils.trimToEmpty(paradavo.getCodigo()));
                Paradero paradero = paraderoAdministrador.getParadero(codigo);
                if (paradero == null) {
                    String direccion = String.format("%s, %s, Región Metropolitana, Chile", paradavo.getNombre(), paradavo.getComuna().getNombre());
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

        List<BusVO> buses = serviPlott.getBuses();
        if (CollectionUtils.isNotEmpty(buses)) {
            String jwt = serviPlott.getJwt();
            for (BusVO busvo : buses) {
                String numeroMicro = busvo.getNumero();
                Micro micro = microAdministrador.crear(numeroMicro);
                LOGGER.info("Cargando micro: '{}'", micro);

                ParadaBusVO busesParadas = serviPlott.getBusParada(numeroMicro, jwt);
                if (busesParadas != null) {
                    for (DireccionVO direccion : busesParadas.getDireccion()) {
                        for (ParadaVO paradavo : direccion.getParadas()) {
                            String codigo = StringUtils.upperCase(StringUtils.trimToEmpty(paradavo.getCodigo()));
                            Paradero paradero = paraderoAdministrador.getParadero(codigo);
                            if (micro != null && paradero != null) {
                                Parada parada = paradaAdministrador.getParada(micro, paradero);
                                if (parada == null) {
                                    parada = new Parada();
                                    parada.setMicro(micro);
                                    parada.setParadero(paradero);
                                    Parada paradaGuardada = paradaAdministrador.guardar(parada);
                                    LOGGER.info("Parada guardada: '{}'", paradaGuardada);
                                }
                            }
                        }
                    }
                }
            }
        }

        return ResponseEntity.ok(new ResponseVO("Datos actualizados"));
    }

}
