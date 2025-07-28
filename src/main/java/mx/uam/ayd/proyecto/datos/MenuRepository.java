package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Menu;

/**
 * 
 * Repositorio para menus
 * 
 *
 */

public interface MenuRepository extends CrudRepository <Menu, Long> {
	
	public Menu findByDia(String dia);

}
