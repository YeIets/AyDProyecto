package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa la entidad Alumno
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ControlMenuSemanal {

    @Autowired
    private VentanaMenuSemanal ventana;

    @Autowired
    private ControlSeleccionMenu controlSeleccionMenu;

    // Campo para guardar el alumno que está usando este módulo
    private Alumno alumno;

    /**
     * CAMBIO: El método ahora recibe al Alumno.
     * @param alumno El alumno para el cual se gestiona el menú.
     */
    public void inicia(Alumno alumno) {
        this.alumno = alumno; // Se guarda el alumno
        Map<String, String[]> menuPorDia = obtenerMenuSemanal();
        // Se pasa el alumno a la ventana por si necesita mostrar su nombre
        ventana.muestra(this, menuPorDia, alumno);
    }

    /**
     * Simulación para obtener el menú semanal.
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
     * CAMBIO: Ahora pasa el alumno al siguiente controlador.
     */
    public void elegirDias() {
        ventana.cerrar();
        // Se pasa el alumno al siguiente paso del flujo
        controlSeleccionMenu.inicia(this.alumno);
    }

    public void regresar() {
        ventana.cerrar();
    }
}