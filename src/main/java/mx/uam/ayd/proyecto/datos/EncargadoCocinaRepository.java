package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.EncargadoCocina;

/**
 * 
 * Repositorio para encargados de cocina
 * 
 *
 */

public interface EncargadoCocinaRepository extends CrudRepository <EncargadoCocina, Long> {
	
	public EncargadoCocina findByNombreAndApellido(String nombre, String apellido);

}
