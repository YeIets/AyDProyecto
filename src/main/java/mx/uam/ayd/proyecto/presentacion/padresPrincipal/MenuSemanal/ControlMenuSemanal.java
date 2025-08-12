package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal;

import java.util.HashMap;
import java.util.Map;

import mx.uam.ayd.proyecto.negocio.ServicioPadre;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;

@Component
public class ControlMenuSemanal {

    private Padre padreSesion;
    @Autowired
    private VentanaMenuSemanal ventana;

    @Autowired
    private ServicioPadre servicioPadre;
    @Autowired
    private ControlSeleccionMenu controlSeleccionMenu;

    /**
     * Método principal para iniciar la ventana del menú semanal
     */
    public void inicia(Padre padre) {
        padreSesion = recuperarPadre(padre.getCorreo());
        Map<String, String[]> menuPorDia = obtenerMenuSemanal();
        ventana.muestra(this, menuPorDia);
    }

    public Padre recuperarPadre(String correo) {
        return servicioPadre.recuperaPadrePorCorreo(correo);
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
     * Método que se ejecuta cuando se presiona el botón "Aceptar"
     */
    public void elegirDias() {
        ventana.cerrar();
        controlSeleccionMenu.inicia(padreSesion);
    }

    /**
     * Método que se ejecuta cuando se presiona el botón "Regresar"
     */
    public void regresar() {
        ventana.cerrar();
        // Aquí podrías enlazar con una ventana anterior (como ControlPadresPrincipal)
    }
}
