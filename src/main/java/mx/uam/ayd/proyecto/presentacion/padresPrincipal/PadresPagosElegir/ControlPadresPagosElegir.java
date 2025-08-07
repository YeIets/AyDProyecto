package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadresPagosElegir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// --- Estos son los imports correctos que me diste ---
import mx.uam.ayd.proyecto.presentacion.Pagos.PagoCaja.ControlPagoCaja;
import mx.uam.ayd.proyecto.presentacion.Pagos.pagoLineaDatos.ControlPagoLineaDatos;
import mx.uam.ayd.proyecto.presentacion.Pagos.pagoEnLinea.ControlPagoLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PedidosSemanales.ControlSeleccionMenu;

@Component
public class ControlPadresPagosElegir {

    @Autowired
    private VentanaPadresPagosElegir ventana;

    // Corregido: El tipo y nombre coinciden con tu import
    @Autowired
    @Lazy
    private ControlPagoLinea controlPagoLinea; // Este es el del "ticket" final

    @Autowired
    @Lazy
    private ControlPagoCaja controlPagoCaja;

    // Corregido: El tipo y nombre coinciden con tu import
    @Autowired
    private ControlPagoLineaDatos controlPagoLineaDatos; // Este es el del formulario de tarjeta

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
        controlPagoLineaDatos.inicia(total, () -> controlPagoLinea.inicia(total));
    }

    public void irAPagoCaja() {
        ventana.cerrar();
        controlPagoCaja.inicia(total);
    }

    public void regresar() {
        ventana.cerrar();
    }

    public ControlSeleccionMenu getControlSeleccionMenu() {
        return controlSeleccionMenu;
    }


}