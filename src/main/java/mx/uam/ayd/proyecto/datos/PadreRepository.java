package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;

/**
 * 
 * Repositorio para padres
 * 
 *
 */

public interface PadreRepository extends CrudRepository <Padre, Long> {
	
	public Padre findByNombreAndApellido(String nombre, String apellido);

}
