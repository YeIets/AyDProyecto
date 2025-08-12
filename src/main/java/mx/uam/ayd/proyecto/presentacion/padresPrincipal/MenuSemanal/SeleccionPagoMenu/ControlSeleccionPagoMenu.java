package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// --- Estos son los imports correctos que me diste ---
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja.ControlPagoCaja;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea.ControlDatosPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea.ControlPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;

@Component
public class ControlSeleccionPagoMenu {

    @Autowired
    private VentanaSeleccionPagoMenu ventana;

    // Corregido: El tipo y nombre coinciden con tu import
    @Autowired
    @Lazy
    private ControlPagoEnLinea controlPagoEnLinea; // Este es el del "ticket" final

    @Autowired
    @Lazy
    private ControlPagoCaja controlPagoCaja;

    // Corregido: El tipo y nombre coinciden con tu import
    @Autowired
    private ControlDatosPagoEnLinea controlDatosPagoEnLinea; // Este es el del formulario de tarjeta

    private ControlSeleccionMenu controlSeleccionMenu;
    private int total;

    public void inicia(int total, ControlSeleccionMenu controlSeleccionMenu) {
        this.total = total;
        this.controlSeleccionMenu = controlSeleccionMenu;
        ventana.muestra(this, total);
    }

    /**
     * MÉTODO CLAVE:
     * Ahora llama a los controladores con los nombres correctos.
     */
    public void irAPagoLinea() {
        ventana.cerrar();

        // Corregido: Se usan las variables correctas
        controlDatosPagoEnLinea.inicia(total, () -> controlPagoEnLinea.inicia(total));
    }

    public void irAPagoCaja() {
        ventana.cerrar();
        controlPagoCaja.inicia(total);
    }

    public void regresar() {
        ventana.cerrar();
    }

}