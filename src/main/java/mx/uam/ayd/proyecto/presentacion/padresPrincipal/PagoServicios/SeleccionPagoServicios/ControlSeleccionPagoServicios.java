package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.SeleccionPagoServicios;

import mx.uam.ayd.proyecto.negocio.ServicioPago;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea.ControlDatosPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja.ControlPagoCaja;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea.ControlPagoEnLinea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControlSeleccionPagoServicios {

    private Padre padreSesion;
    @Autowired
    private VentanaSeleccionPagoServicios ventana;

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

    private ControlSeleccionPagoServicios controlSeleccionPagoServicios;
    private List<String> serviciosSeleccionados;
    private int total;

    public void inicia(int total, ControlSeleccionPagoServicios controlSeleccionPagoServicios, List<String> serviciosSeleccionados, Padre padre) {
        this.total = total;
        this.controlSeleccionPagoServicios = controlSeleccionPagoServicios;
        this.serviciosSeleccionados = serviciosSeleccionados;
        this.padreSesion = padre;
        ventana.muestra(this, total);
    }

    /**
     * MÉTODO CLAVE:
     * Ahora llama a los controladores con los nombres correctos.
     */
    public void irAPagoLinea() {
        ventana.cerrar();
        controlDatosPagoEnLinea.inicia(total, () -> controlPagoEnLinea.inicia(total));
        String servicios = String.join(", ", serviciosSeleccionados);
        servicioPago.crearPagoDeServiciosEnLinea(total,padreSesion,servicios);
    }

    public void irAPagoCaja() {
        ventana.cerrar();
        String servicios = String.join(", ", serviciosSeleccionados);
        servicioPago.crearPagoDeServiciosCaja(total,padreSesion,servicios);
        controlPagoCaja.inicia(total);
    }

    public void regresar() {
        ventana.cerrar();
    }

}