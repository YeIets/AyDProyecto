package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu;

import mx.uam.ayd.proyecto.negocio.ServicioPadre;
import mx.uam.ayd.proyecto.negocio.ServicioPago;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// --- Estos son los imports correctos que me diste ---
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja.ControlPagoCaja;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea.ControlDatosPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea.ControlPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;

import java.util.List;

@Component
public class ControlSeleccionPagoMenu {

    private Padre padreSesion;
    @Autowired
    private VentanaSeleccionPagoMenu ventana;

    @Autowired
    private ServicioPadre servicioPadre;
    @Autowired
    private ServicioPago servicioPago;
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
    private List<String> diasSeleccionados;
    private int total;

    public void inicia(int total, ControlSeleccionMenu controlSeleccionMenu, List<String> diasSeleccionados, Padre padre) {
        this.total = total;
        this.controlSeleccionMenu = controlSeleccionMenu;
        this.diasSeleccionados = diasSeleccionados;
        this.padreSesion = recuperarPadre(padre.getCorreo());
        ventana.muestra(this, total);
    }

    public Padre recuperarPadre(String correo) {
        return servicioPadre.recuperaPadrePorCorreo(correo);
    }

    /**
     * MÉTODO CLAVE:
     * Ahora llama a los controladores con los nombres correctos.
     */
    public void irAPagoLinea() {
        ventana.cerrar();
        controlDatosPagoEnLinea.inicia(total, () -> controlPagoEnLinea.inicia(total));
        String diasAPagar = String.join(", ", diasSeleccionados);
        servicioPago.crearPagoDeMenuEnLinea(total,padreSesion,diasAPagar);
    }

    public void irAPagoCaja() {
        ventana.cerrar();
        String diasAPagar = String.join(", ", diasSeleccionados);
        servicioPago.crearPagoDeMenuCaja(total,padreSesion,diasAPagar);
        controlPagoCaja.inicia(total);
    }

    public void regresar() {
        ventana.cerrar();
    }

}