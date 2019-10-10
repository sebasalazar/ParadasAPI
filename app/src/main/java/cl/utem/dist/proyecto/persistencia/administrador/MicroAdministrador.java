package cl.utem.dist.proyecto.persistencia.administrador;

import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.TipoRecorrido;
import cl.utem.dist.proyecto.persistencia.repositorio.MicroRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("microAdministrador")
public class MicroAdministrador {

    @Autowired
    private transient MicroRepositorio microRepositorio;

    public Micro getMicro(Long id) {
        Micro micro = null;
        if (id != null && id > 0) {
            micro = microRepositorio.getOne(id);
        }
        return micro;
    }

    public Micro getMicro(String recorrido, TipoRecorrido tipo) {
        Micro micro = null;
        if (StringUtils.isNotBlank(recorrido) && tipo != null) {
            micro = microRepositorio.findByRecorridoAndTipo(recorrido, tipo);
        }
        return micro;
    }

    public List<Micro> getMicros() {
        return microRepositorio.findAll();
    }

    public List<Micro> getMicros(String recorrido) {
        List<Micro> micros = new ArrayList<>();
        if (StringUtils.isNotBlank(recorrido)) {
            micros = microRepositorio.findByRecorrido(recorrido);
        }
        return micros;
    }

    public List<Micro> getMicros(TipoRecorrido recorrido) {
        List<Micro> micros = new ArrayList<>();
        if (recorrido != null) {
            micros = microRepositorio.findByTipo(recorrido);
        }
        return micros;
    }

    @Transactional
    public Micro crear(final String linea) {
        Micro micro = null;
        if (StringUtils.isNotBlank(linea)) {

        }
        return micro;
    }

    @Transactional
    public Micro guardar(Micro micro) {
        Micro guardado = null;
        if (micro != null) {
            guardado = microRepositorio.save(micro);
        }
        return guardado;
    }

    @Transactional
    public boolean delete(Micro micro) {
        boolean ok = false;
        if (micro != null) {
            microRepositorio.delete(micro);
            ok = true;
        }
        return ok;
    }
}
