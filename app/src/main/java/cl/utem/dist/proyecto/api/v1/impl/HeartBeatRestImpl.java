package cl.utem.dist.proyecto.api.v1.impl;

import cl.utem.dist.proyecto.api.v1.HeartBeatRest;
import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/v1"}, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
public class HeartBeatRestImpl implements HeartBeatRest, Serializable {

    @Override
    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET, consumes = {"*/*"}, produces = "application/json; charset=UTF-8")
    public ResponseEntity heartbeat() {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
