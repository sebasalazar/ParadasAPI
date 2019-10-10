package cl.utem.dist.proyecto.persistencia.repositorio;

import cl.utem.dist.proyecto.persistencia.modelo.Micro;
import cl.utem.dist.proyecto.persistencia.modelo.TipoRecorrido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroRepositorio extends JpaRepository<Micro, Long> {

    Micro findByRecorridoAndTipo(String recorrido, TipoRecorrido tipo);

    List<Micro> findByRecorrido(String recorrido);

    List<Micro> findByTipo(TipoRecorrido tipo);
}
