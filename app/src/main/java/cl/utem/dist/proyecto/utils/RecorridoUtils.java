package cl.utem.dist.proyecto.utils;

import cl.utem.dist.proyecto.persistencia.modelo.TipoRecorrido;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class RecorridoUtils implements Serializable {

    private RecorridoUtils() {
        throw new IllegalStateException("No instanciable");
    }

    public static String getRecorrido(final String linea) {
        String recorrido = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(linea)) {
            String valor = StringUtils.upperCase(StringUtils.trimToEmpty(linea));
            String ultimo = StringUtils.substring(valor, - 1);
            if (NumberUtils.isDigits(ultimo)) {
                recorrido = valor;
            } else {
                recorrido = StringUtils.chop(valor);
            }
        }
        return recorrido;
    }

    public static TipoRecorrido getTipoRecorridoPorLineaBus(final String linea) {
        TipoRecorrido tipo = TipoRecorrido.NORMAL;
        if (StringUtils.isNotBlank(linea)) {
            String valor = StringUtils.upperCase(StringUtils.trimToEmpty(linea));
            String ultimo = StringUtils.substring(valor, - 1);
            if (!NumberUtils.isDigits(ultimo)) {
                if (StringUtils.equals(ultimo, "C")) {
                    tipo = TipoRecorrido.CORTO;
                } else if (StringUtils.equals(ultimo, "E")) {
                    tipo = TipoRecorrido.EXPRESO;
                }
            }
        }
        return tipo;
    }

    public static TipoRecorrido getTipoRecorrido(final String tipo) {
        TipoRecorrido tr = null;

        String tipoStr = StringUtils.upperCase(StringUtils.trimToEmpty(tipo));
        if (StringUtils.equalsIgnoreCase(tipoStr, TipoRecorrido.CORTO.name())) {
            tr = TipoRecorrido.CORTO;
        } else if (StringUtils.equalsIgnoreCase(tipoStr, TipoRecorrido.ESPECIAL.name())) {
            tr = TipoRecorrido.ESPECIAL;
        } else if (StringUtils.equalsIgnoreCase(tipoStr, TipoRecorrido.EXPRESO.name())) {
            tr = TipoRecorrido.EXPRESO;
        } else if (StringUtils.equalsIgnoreCase(tipoStr, TipoRecorrido.NORMAL.name())) {
            tr = TipoRecorrido.NORMAL;
        } else if (StringUtils.equalsIgnoreCase(tipoStr, TipoRecorrido.TEMPORAL.name())) {
            tr = TipoRecorrido.TEMPORAL;
        }

        return tr;
    }
}
