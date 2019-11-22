package cl.utem.dist.proyecto.persistencia.repositorio;

import cl.utem.dist.proyecto.persistencia.modelo.Paradero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("paraderoRepositorio")
public interface ParaderoRepositorio extends JpaRepository<Paradero, Long> {

    public Paradero findByCodigo(String codigo);

    public List<Paradero> findByDireccionContains(String direccion);
}
