package cl.utem.dist.proyecto.vo;

import cl.utem.dist.proyecto.persistencia.modelo.BaseBean;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class HttpVO extends BaseBean {

    private Integer state = null;
    private String response = null;

    public HttpVO() {
        this.state = null;
        this.response = null;
    }

    public HttpVO(Integer state, String response) {
        this.state = state;
        this.response = response;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isOk() {
        boolean ok = false;
        if (state != null) {
            ok = (state >= 200 && state < 300);
        }
        return ok;
    }
}
