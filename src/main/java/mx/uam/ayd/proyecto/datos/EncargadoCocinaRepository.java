package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.EncargadoCocina;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface EncargadoCocinaRepository extends CrudRepository<EncargadoCocina, Long> {
    Optional<EncargadoCocina> findByCorreo(String correo);
}