package mx.uam.ayd.proyecto.presentacion.padreMenuSemanal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.seleccionMenu.ControlSeleccionMenu;

@Component
public class ControlPadreMenuSemanal {

    @Autowired
    private VentanaPadreMenuSemanal ventana;

    @Autowired
    private ControlSeleccionMenu controlSeleccionMenu;

    /**
     * Método principal para iniciar la ventana del menú semanal
     */
    public void inicia() {
        Map<String, String[]> menuPorDia = obtenerMenuSemanal();
        ventana.muestra(this, menuPorDia);
    }

    /**
     * Simulación de obtener el menú semanal (puedes reemplazarlo con llamadas a servicio o repositorio)
     */
    private Map<String, String[]> obtenerMenuSemanal() {
        Map<String, String[]> menu = new HashMap<>();

        menu.put("Lunes", new String[]{"Tacos", "Agua de Jamaica", "Manzana", "Gelatina de Fresa"});
        menu.put("Martes", new String[]{"Pasta", "Agua de Limón", "Plátano", "Gelatina de Mango"});
        menu.put("Miercoles", new String[]{"Arroz con Pollo", "Agua Natural", "Naranja", "Gelatina de Uva"});
        menu.put("Jueves", new String[]{"Enchiladas", "Agua de Horchata", "Papaya", "Gelatina de Limón"});
        menu.put("Viernes", new String[]{"Hamburguesa", "Agua de Tamarindo", "Pera", "Gelatina de Fresa"});

        return menu;
    }

    /**
     * Método que se ejecuta cuando se presiona el botón "Elegir Días"
     */
    public void elegirDias() {
        ventana.cerrar();
        controlSeleccionMenu.inicia(); // Aquí conectamos con la ventana de selección
    }

    /**
     * Método que se ejecuta cuando se presiona el botón "Regresar"
     */
    public void regresar() {
        ventana.cerrar();
        // Aquí puedes enlazar a la ventana anterior si la tienes
        // Ejemplo:
        // controlVentanaPadrePrincipal.inicia();
    }
}
