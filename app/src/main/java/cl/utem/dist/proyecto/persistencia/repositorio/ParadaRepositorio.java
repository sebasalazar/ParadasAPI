package cl.utem.dist.proyecto.persistencia.repositorio;

import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.Parada;
import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paradaRepositorio")
public interface ParadaRepositorio extends JpaRepository<Parada, Long> {

    public Parada findByMicroAndParadero(Micro micro, Paradero paradero);

    public List<Parada> findByMicro(Micro micro);

    public List<Parada> findByParadero(Paradero paradero);
}
