package cl.utem.dist.proyecto.utils;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import cl.utem.dist.proyecto.vo.serviplott.BusVO;
import cl.utem.dist.proyecto.vo.serviplott.JWTVO;
import cl.utem.dist.proyecto.vo.serviplott.ParadaVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils implements Serializable {

    private static final long serialVersionUID = 6297370703457567744L;
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
        throw new AssertionError();
    }

    public static String parse(Boolean object) {
        String json = StringUtils.EMPTY;
        try {
            if (object != null) {
                ObjectMapper mapper = new ObjectMapper();
                json = StringUtils.trimToEmpty(mapper.writeValueAsString(object));
            }
        } catch (JsonProcessingException e) {
            json = StringUtils.EMPTY;
            LOGGER.error("Error al convertir a Json: {}", e.toString());
        }
        return json;
    }

    public static String parse(Long object) {
        String json = StringUtils.EMPTY;
        try {
            if (object != null) {
                ObjectMapper mapper = new ObjectMapper();
                json = StringUtils.trimToEmpty(mapper.writeValueAsString(object));
            }
        } catch (JsonProcessingException e) {
            json = StringUtils.EMPTY;
            LOGGER.error("Error al convertir a Json: {}", e.toString());
        }
        return json;
    }

    public static String parse(BaseBean object) {
        String json = StringUtils.EMPTY;
        try {
            if (object != null) {
                ObjectMapper mapper = new ObjectMapper();
                json = StringUtils.trimToEmpty(mapper.writeValueAsString(object));
            }
        } catch (JsonProcessingException e) {
            json = StringUtils.EMPTY;
            LOGGER.error("Error al convertir a Json: {}", e.toString());
        }
        return json;
    }

    public static JWTVO getJWTVO(final String json) {
        JWTVO vo = null;
        try {
            if (StringUtils.isNotBlank(json)) {
                ObjectMapper mapper = new ObjectMapper();
                vo = mapper.readValue(json, JWTVO.class);
            }
        } catch (IOException e) {
            vo = null;
            LOGGER.error("Error al convertir a JWTVO: {}", e.getMessage());
        }
        return vo;
    }

    public static List<BusVO> getBusesVO(final String json) {
        List<BusVO> vos = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(json)) {
                ObjectMapper mapper = new ObjectMapper();
                vos = new ArrayList<>(mapper.readValue(json, new TypeReference<List<BusVO>>() {
                }));
            }
        } catch (IOException e) {
            vos = new ArrayList<>();
            LOGGER.error("Error al convertir a BusVO: {}", e.getMessage());
        }
        return vos;
    }

    public static List<ParadaVO> getParadasVO(final String json) {
        List<ParadaVO> vos = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(json)) {
                ObjectMapper mapper = new ObjectMapper();
                vos = new ArrayList<>(mapper.readValue(json, new TypeReference<List<ParadaVO>>() {
                }));
            }
        } catch (IOException e) {
            vos = new ArrayList<>();
            LOGGER.error("Error al convertir a ParadaVO: {}", e.getMessage());
        }
        return vos;
    }
}
