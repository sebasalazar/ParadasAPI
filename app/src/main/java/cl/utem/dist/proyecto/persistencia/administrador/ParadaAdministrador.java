package cl.utem.dist.proyecto.persistencia.administrador;

import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.Parada;
import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import cl.utem.dist.proyecto.persistencia.repositorio.ParadaRepositorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paradaAdministrador")
public class ParadaAdministrador implements Serializable {

    private static final long serialVersionUID = 8734332084773068800L;

    @Autowired
    private transient ParadaRepositorio paradaRepositorio;

    public Parada getParada(final Long id) {
        Parada parada = null;
        if (id != null && id > 0) {
            parada = paradaRepositorio.getOne(id);
        }
        return parada;
    }

    public Parada getParada(final Micro micro, final Paradero paradero) {
        Parada parada = null;
        if (micro != null && paradero != null) {
            parada = paradaRepositorio.findByMicroAndParadero(micro, paradero);
        }
        return parada;
    }

    public List<Parada> getParadas(final Micro micro) {
        List<Parada> paradas = new ArrayList<>();
        if (micro != null) {
            paradas = paradaRepositorio.findByMicro(micro);
        }
        return paradas;
    }

    public List<Parada> getParadas(final Paradero paradero) {
        List<Parada> paradas = new ArrayList<>();
        if (paradero != null) {
            paradas = paradaRepositorio.findByParadero(paradero);
        }
        return paradas;
    }

    @Transactional
    public Parada guardar(final Parada parada) {
        Parada guardado = null;
        if (parada != null) {
            guardado = paradaRepositorio.save(parada);
        }
        return guardado;
    }

    @Transactional
    public boolean eliminar(final Parada parada) {
        boolean ok = false;
        if (parada != null) {
            paradaRepositorio.delete(parada);
            ok = true;
        }
        return ok;
    }
}
