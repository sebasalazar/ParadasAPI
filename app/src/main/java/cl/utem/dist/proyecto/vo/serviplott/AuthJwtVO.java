package cl.utem.dist.proyecto.vo.serviplott;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "auth"
})
public class AuthJwtVO extends BaseBean {

    @JsonProperty("auth")
    public AuthVO auth;
    private final static long serialVersionUID = -7760762151007463590L;

    public AuthJwtVO() {
        this.auth = new AuthVO();
    }

    public AuthJwtVO(String email, String password) {
        this.auth = new AuthVO();
        this.auth.setEmail(email);
        this.auth.setPassword(password);
    }

    public AuthJwtVO(AuthVO auth) {
        this.auth = auth;
    }

    public AuthVO getAuth() {
        return auth;
    }

    public void setAuth(AuthVO auth) {
        this.auth = auth;
    }
}
