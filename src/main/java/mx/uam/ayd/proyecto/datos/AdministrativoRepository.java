package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Administrativo;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface AdministrativoRepository extends CrudRepository<Administrativo, Long> {
    Optional<Administrativo> findByCorreo(String correo);
}