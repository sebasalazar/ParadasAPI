package cl.utem.dist.proyecto.api.v1.impl;

import cl.utem.dist.proyecto.api.v1.RecorridoRest;
import cl.utem.dist.proyecto.persistencia.administrador.MicroAdministrador;
import cl.utem.dist.proyecto.persistencia.administrador.ParadaAdministrador;
import cl.utem.dist.proyecto.persistencia.administrador.ParaderoAdministrador;
import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.Parada;
import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import cl.utem.dist.proyecto.persistencia.modelo.TipoRecorrido;
import cl.utem.dist.proyecto.utils.RecorridoUtils;
import cl.utem.dist.proyecto.vo.response.ErrorVO;
import cl.utem.dist.proyecto.vo.response.MicroBusVO;
import cl.utem.dist.proyecto.vo.response.ParadaVO;
import cl.utem.dist.proyecto.vo.response.ParaderoVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/v1/recorridos"}, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
public class RecorridoRestImpl implements RecorridoRest, Serializable {

    private static final long serialVersionUID = 4967308304284133376L;

    @Autowired
    private transient MicroAdministrador microAdministrador;
    @Autowired
    private transient ParaderoAdministrador paraderoAdministrador;
    @Autowired
    private transient ParadaAdministrador paradaAdministrador;

    @Override
    @RequestMapping(value = "/microbus", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getBus(@RequestParam(name = "numero") String numero, @RequestParam(name = "tipo") String tipo) {

        TipoRecorrido tipoRecorrido = RecorridoUtils.getTipoRecorrido(tipo);
        if (tipoRecorrido == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorVO("El tipo de recorrido es incorrecto"));
        }

        if (StringUtils.isBlank(numero)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorVO("El número del microbus es obligatorio"));
        }

        Micro micro = microAdministrador.getMicro(numero, tipoRecorrido);
        if (micro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorVO("No se ha encontrado el microbus"));
        }

        return ResponseEntity.ok(new MicroBusVO(micro));
    }

    @Override
    @RequestMapping(value = "/microbuses", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getBuses() {
        List<MicroBusVO> microbuses = new ArrayList<>();

        List<Micro> micros = microAdministrador.getMicros();
        if (CollectionUtils.isEmpty(micros)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorVO(""));
        }

        for (Micro micro : micros) {
            MicroBusVO vo = new MicroBusVO(micro);
            microbuses.add(vo);
        }

        return ResponseEntity.ok(microbuses);
    }

    @Override
    @RequestMapping(value = "/recorridos/tipos", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getTiposRecorridos() {
        List<String> tipos = new ArrayList<>();
        TipoRecorrido[] valores = TipoRecorrido.values();
        for (TipoRecorrido val : valores) {
            tipos.add(val.name());
        }

        return ResponseEntity.ok(tipos);
    }

    @Override
    @RequestMapping(value = "/paradero", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getParadero(@RequestParam(name = "codigo") String codigo) {
        if (StringUtils.isBlank(codigo)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorVO("El código es obligatorio"));
        }

        Paradero paradero = paraderoAdministrador.getParadero(codigo);
        if (paradero == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorVO("No se ha encontrado el paradero"));
        }

        return ResponseEntity.ok(new ParaderoVO(paradero));
    }

    @Override
    @RequestMapping(value = "/paraderos", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getParaderos() {
        List<ParaderoVO> vos = new ArrayList<>();

        List<Paradero> paraderos = paraderoAdministrador.getParaderos();
        if (CollectionUtils.isEmpty(paraderos)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorVO("No se ha encontrado paraderos"));
        }

        for (Paradero paradero : paraderos) {
            vos.add(new ParaderoVO(paradero));
        }

        return ResponseEntity.ok(vos);
    }

    @Override
    @RequestMapping(value = "/paradas", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity getParadas(@RequestParam(name = "numeroBus") String numeroBus) {
        List<ParadaVO> vos = new ArrayList<>();
        if (StringUtils.isBlank(numeroBus)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ErrorVO("El número del microbus es obligatorio"));
        }

        List<Parada> paradas = paradaAdministrador.getParadas(numeroBus);
        if (CollectionUtils.isEmpty(paradas)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorVO("No se ha encontrado paradas"));
        }

        for (Parada parada : paradas) {
            vos.add(new ParadaVO(parada));
        }

        return ResponseEntity.ok(vos);
    }
}
