package cl.utem.dist.proyecto.persistencia.repositorio;

import cl.utem.dist.proyecto.persistencia.modelo.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paradaRepositorio")
public interface ParadaRepositorio extends JpaRepository<Parada, Long> {

}
