package cl.utem.dist.proyecto;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

public class RecorridoUtils implements Serializable {

    private RecorridoUtils() {
        throw new IllegalStateException("No instanciable");
    }

    public String getRecorrido(final String linea) {
        String recorrido = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(linea)) {
            String valor = StringUtils.upperCase(StringUtils.trimToEmpty(linea));
            String[] arreglo = StringUtils.split(valor, StringUtils.EMPTY);
        }
        return recorrido;
    }
}
