package cl.utem.dist.proyecto.servicio;

import cl.utem.dist.proyecto.utils.HttpClientUtils;
import cl.utem.dist.proyecto.utils.JsonUtils;
import cl.utem.dist.proyecto.vo.HttpVO;
import cl.utem.dist.proyecto.vo.serviplott.AuthJwtVO;
import cl.utem.dist.proyecto.vo.serviplott.BusVO;
import cl.utem.dist.proyecto.vo.serviplott.JWTVO;
import cl.utem.dist.proyecto.vo.serviplott.ParadaBusVO;
import cl.utem.dist.proyecto.vo.serviplott.ParadaVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("serviPlott")
public class ServiPlott implements Serializable {

    private static final long serialVersionUID = 9165127032412163072L;

    @Value("${SERVIPLOTT_USERNAME}")
    private String username;
    @Value("${SERVIPLOTT_PASSWORD}")
    private String password;
    private static final String SERVIPLOT_URL = "http://red.serviplott.cl";
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiPlott.class);

    public String getJwt() {
        String key = StringUtils.EMPTY;
        try {
            AuthJwtVO authVO = new AuthJwtVO(username, password);
            String url = String.format("%s/api/user_token", SERVIPLOT_URL);
            HttpVO post = HttpClientUtils.postJson(url, authVO);
            if (post.isOk()) {
                JWTVO vo = JsonUtils.getJWTVO(post.getResponse());
                key = vo.getJwt();
            }

        } catch (Exception e) {
            key = StringUtils.EMPTY;
            LOGGER.error("Error al obtener un api key: '{}'", e.getMessage());
            LOGGER.debug("Error al obtener un api key: '{}'", e.getMessage(), e);
        }
        return key;
    }

    public List<BusVO> getBuses() {
        List<BusVO> buses = new ArrayList<>();
        try {
            String jwt = getJwt();
            String url = String.format("%s/api/buses", SERVIPLOT_URL);
            HttpVO get = HttpClientUtils.get(url, jwt);
            if (get.isOk()) {
                buses = JsonUtils.getBusesVO(get.getResponse());
            }
        } catch (Exception e) {
            buses = new ArrayList<>();
            LOGGER.error("Error al obtener buses: '{}'", e.getMessage());
            LOGGER.debug("Error al obtener buses: '{}'", e.getMessage(), e);
        }
        return buses;
    }

    public List<ParadaVO> getParaderos() {
        List<ParadaVO> paradas = new ArrayList<>();
        try {
            String jwt = getJwt();
            String url = String.format("%s/api/paradas/todas", SERVIPLOT_URL);
            HttpVO get = HttpClientUtils.get(url, jwt);
            if (get.isOk()) {
                paradas = JsonUtils.getParadasVO(get.getResponse());
            }
        } catch (Exception e) {
            paradas = new ArrayList<>();
            LOGGER.error("Error al obtener paradas: '{}'", e.getMessage());
            LOGGER.debug("Error al obtener paradas: '{}'", e.getMessage(), e);
        }

        return paradas;
    }

    public ParadaBusVO getBusParada(String bus, String jwt) {
        ParadaBusVO paradas = null;
        try {
            if (StringUtils.isBlank(jwt)) {
                jwt = getJwt();
            }

            String url = String.format("%s/api/buses/numero/%s", SERVIPLOT_URL, bus);
            HttpVO get = HttpClientUtils.get(url, jwt);
            if (get.isOk()) {
                paradas = JsonUtils.getParadaBusVO(get.getResponse());
            }
        } catch (Exception e) {
            paradas = null;
            LOGGER.error("Error al obtener paradas: '{}'", e.getMessage());
            LOGGER.debug("Error al obtener paradas: '{}'", e.getMessage(), e);
        }

        return paradas;
    }
}
