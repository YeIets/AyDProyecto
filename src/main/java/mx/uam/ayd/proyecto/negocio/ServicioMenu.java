package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.MenuRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
/**
 * Servicio relacionado con los menus
 *
 */
public class ServicioMenu {
	
	private final MenuRepository menuRepository;
	
	@Autowired
	public ServicioMenu(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	
	/**
	 * 
	 * Recupera todos los grupos
	 * 
	 * @return una lista de grupos vacía o con los grupos existentes
	 */
	public List <Menu> recuperaMenus() {
		List <Menu> menus = new ArrayList<>();
		
		for(Menu menu : menuRepository.findAll()) {
			menus.add(menu);
		}
				
		return menus;
	}

}
