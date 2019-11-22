package cl.utem.dist.proyecto.persistencia.modelo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;

/**
 *
 * @author Sebastián Salazar Molina
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = -572159829033690039L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(
                this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
