package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional; // Se requiere la importación para Optional

/**
 * Repositorio para la entidad Padre.
 */
public interface PadreRepository extends CrudRepository<Padre, Long> {

    /**
     * Busca un Padre por su dirección de correo electrónico.
     * Spring Data JPA implementará este método automáticamente.
     *
     * @param correo El correo electrónico a buscar.
     * @return un Optional que contiene al Padre si se encuentra, o un Optional vacío si no.
     */
    Optional<Padre> findByCorreo(String correo);

}