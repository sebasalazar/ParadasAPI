package cl.utem.dist.proyecto.persistencia.administrador;

import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import cl.utem.dist.proyecto.persistencia.repositorio.ParaderoRepositorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paraderoAdministrador")
public class ParaderoAdministrador implements Serializable {

    private static final long serialVersionUID = 8177260310100468736L;

    @Autowired
    private transient ParaderoRepositorio paraderoRepositorio;

    public Paradero getParadero(final Long id) {
        Paradero paradero = null;
        if (id != null && id > 0) {
            paradero = paraderoRepositorio.getOne(id);
        }
        return paradero;
    }

    public Paradero getParadero(final String codigo) {
        Paradero paradero = null;
        if (StringUtils.isNotBlank(codigo)) {
            paradero = paraderoRepositorio.findByCodigo(codigo);
        }
        return paradero;
    }

    public List<Paradero> getParaderos(final String direccion) {
        List<Paradero> paraderos = new ArrayList<>();
        if (StringUtils.isNotBlank(direccion)) {
            paraderos = paraderoRepositorio.findByDireccionContains(direccion);
        }
        return paraderos;
    }

    public List<Paradero> getParaderos() {
        return paraderoRepositorio.findAll();
    }

    @Transactional
    public Paradero guardar(final Paradero paradero) {
        Paradero guardado = null;
        if (paradero != null) {
            guardado = paraderoRepositorio.save(paradero);
        }
        return guardado;
    }

    @Transactional
    public boolean eliminar(final Paradero paradero) {
        boolean ok = false;
        if (paradero != null) {
            paraderoRepositorio.delete(paradero);
            ok = true;
        }
        return ok;
    }
}
