package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PedidosSemanales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlSeleccionMenu {

    @Autowired
    private VentanaSeleccionMenu ventanaSeleccionMenu;

    /**
     * Inicia la ventana de selección del menú.
     */
    public void inicia() {
        ventanaSeleccionMenu.muestra();
    }

    /**
     * Método que se llama cuando el usuario presiona "Volver al menú".
     * Aquí puedes implementar la lógica para regresar a la ventana anterior.
     */
    public void volverAlMenuAnterior() {
        System.out.println("Regresando al menú anterior...");
        // Aquí puedes agregar lógica para volver al menú semanal, por ejemplo:
        // controlPadreMenuSemanal.inicia(); si lo inyectas
    }
}
