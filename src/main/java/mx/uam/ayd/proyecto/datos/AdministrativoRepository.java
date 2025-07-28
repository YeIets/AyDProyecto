package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Administrativo;

/**
 * 
 * Repositorio para administrativos
 * 
 *
 */

public interface AdministrativoRepository extends CrudRepository <Administrativo, Long> {
	
	public Administrativo findByNombreAndApellido(String nombre, String apellido);

}
