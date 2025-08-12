package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.SeleccionPagoServicios;

import mx.uam.ayd.proyecto.negocio.ServicioPago;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // CAMBIO: Se importa la clase Alumno
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
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
    @Autowired
    @Lazy
    private ControlPagoEnLinea controlPagoEnLinea;

    @Autowired
    @Lazy
    private ControlPagoCaja controlPagoCaja;

    @Autowired
    private ControlDatosPagoEnLinea controlDatosPagoEnLinea;

    private List<String> serviciosSeleccionados;
    private int total;

    // Se eliminó la variable sin usar 'controlSeleccionPagoServicios' de la firma
    public void inicia(int total, List<String> serviciosSeleccionados, Padre padre) {
        this.total = total;
        this.serviciosSeleccionados = serviciosSeleccionados;
        this.padreSesion = padre;
        ventana.muestra(this, total);
    }

    /**
     * Inicia el flujo de pago en línea, ahora pasando el Alumno.
     */
    public void irAPagoLinea() {
        ventana.cerrar();
        // CAMBIO: Se obtiene el alumno desde el padre y se pasa como tercer argumento.
        // También se corrige la llamada DENTRO de la lambda.
        Alumno alumno = padreSesion.getAlumno();
        controlDatosPagoEnLinea.inicia(
                total,
                () -> controlPagoEnLinea.inicia(total, alumno),
                alumno
        );
        String servicios = String.join(", ", serviciosSeleccionados);
        servicioPago.crearPagoDeServiciosEnLinea(total, padreSesion, servicios);
    }

    /**
     * Inicia el flujo de pago en caja, ahora pasando el Alumno.
     */
    public void irAPagoCaja() {
        ventana.cerrar();
        // CAMBIO: Se obtiene el alumno y se pasa al controlador de pago en caja.
        Alumno alumno = padreSesion.getAlumno();
        controlPagoCaja.inicia(total, alumno);
        String servicios = String.join(", ", serviciosSeleccionados);
        servicioPago.crearPagoDeServiciosCaja(total, padreSesion, servicios);
    }

    public void regresar() {
        ventana.cerrar();
    }

}