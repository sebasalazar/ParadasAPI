package cl.utem.dist.proyecto.vo.serviplott;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWTVO extends BaseBean {

    private final static long serialVersionUID = -1403782338864752428L;

    @JsonProperty("jwt")
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
