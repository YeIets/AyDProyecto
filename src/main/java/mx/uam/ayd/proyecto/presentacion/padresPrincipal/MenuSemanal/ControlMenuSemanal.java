package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.negocio.modelo.Menu; // CAMBIO: Se usa tu entidad Menu
import mx.uam.ayd.proyecto.negocio.ServicioMenu; // CAMBIO: Se usa tu ServicioMenu
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;

@Component
public class ControlMenuSemanal {

    @Autowired
    private VentanaMenuSemanal ventana;

    @Autowired
    private ControlSeleccionMenu controlSeleccionMenu;

    // CAMBIO: Se inyecta tu servicio existente
    @Autowired
    private ServicioMenu servicioMenu;

    private Alumno alumno;

    public void inicia(Alumno alumno) {
        this.alumno = alumno;
        Map<String, String[]> menuPorDia = obtenerMenuSemanalDesdeServicio();
        ventana.muestra(this, menuPorDia);
    }

    /**
     * CAMBIO: Este método ahora usa tu servicio y se adapta a los nombres de
     * los campos de tu entidad 'Menu' (comida, agua, etc.).
     */
    private Map<String, String[]> obtenerMenuSemanalDesdeServicio() {
        Map<String, String[]> menuTransformado = new HashMap<>();
        List<Menu> menus = servicioMenu.recuperaMenus();

        for (Menu menu : menus) {
            menuTransformado.put(menu.getDia(), new String[]{
                    menu.getComida(),
                    menu.getAgua(),
                    menu.getFruta(),
                    menu.getGelatina() // El orden debe coincidir con como lo usa la ventana
            });
        }

        return menuTransformado;
    }

    public void elegirDias() {
        ventana.cerrar();
        controlSeleccionMenu.inicia(this.alumno);
    }

    public void regresar() {
        ventana.cerrar();
    }
}