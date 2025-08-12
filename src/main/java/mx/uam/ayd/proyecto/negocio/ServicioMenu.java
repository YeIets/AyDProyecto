package mx.uam.ayd.proyecto.negocio;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.datos.MenuRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Se importa Optional

@Service
public class ServicioMenu {

    @Autowired
    private MenuRepository menuRepository;

    @PostConstruct
    public void inicializar() {
        if (menuRepository.count() == 0) {
            menuRepository.save(new Menu(null, "Tacos", "Agua de Jamaica", "Gelatina de Fresa", "Manzana", "Lunes"));
            menuRepository.save(new Menu(null, "Pasta", "Agua de Limón", "Gelatina de Mango", "Plátano", "Martes"));
            menuRepository.save(new Menu(null, "Arroz con Pollo", "Agua Natural", "Gelatina de Uva", "Naranja", "Miercoles"));
            menuRepository.save(new Menu(null, "Enchiladas", "Agua de Horchata", "Gelatina de Limón", "Papaya", "Jueves"));
            menuRepository.save(new Menu(null, "Hamburguesa", "Agua de Tamarindo", "Gelatina de Fresa", "Pera", "Viernes"));
        }
    }

    public List<Menu> recuperaMenus() {
        List<Menu> menus = new ArrayList<>();
        menuRepository.findAll().forEach(menus::add);
        return menus;
    }

    /**
     * ✅ NUEVO MÉTODO: Busca un menú por día y actualiza sus platillos.
     * @return true si se actualizó correctamente, false si no se encontró el día.
     */
    public boolean actualizarPlatilloDelDia(String dia, String platillo, String bebida, String fruta, String postre) {
        Menu menuExistente = menuRepository.findByDia(dia);

        if (menuExistente != null) {
            // Si el día existe, actualizamos sus datos
            menuExistente.setComida(platillo);
            menuExistente.setAgua(bebida);
            menuExistente.setFruta(fruta);
            menuExistente.setGelatina(postre);
            menuRepository.save(menuExistente); // Guardamos la entidad actualizada
            return true;
        }

        return false; // No se encontró el día, no se actualizó nada
    }
}