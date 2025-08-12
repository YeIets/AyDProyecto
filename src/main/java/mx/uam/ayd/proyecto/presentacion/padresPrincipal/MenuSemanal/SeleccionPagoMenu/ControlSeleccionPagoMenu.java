package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja.ControlPagoCaja;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea.ControlDatosPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea.ControlPagoEnLinea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author      Nombre del autor(es)
 * @since       1.4
 *
 * Módulo de control que gestiona la selección del método de pago.
 */
@Component
public class ControlSeleccionPagoMenu {

    @Autowired
    private VentanaSeleccionPagoMenu ventana;

    @Autowired
    @Lazy
    private ControlPagoEnLinea controlPagoEnLinea;

    @Autowired
    @Lazy
    private ControlPagoCaja controlPagoCaja;

    @Autowired
    private ControlDatosPagoEnLinea controlDatosPagoEnLinea;

    private List<String> conceptosAPagar;
    private int total;
    private ControlSeleccionMenu controlSeleccionMenu;
    private Alumno alumno;

    /**
     * Inicia el flujo para el pago del Menú Semanal (flujo original).
     * @param total El monto a pagar.
     * @param controlSeleccionMenu El controlador del flujo de menú.
     * @param alumno El alumno asociado a la transacción.
     */
    public void inicia(int total, ControlSeleccionMenu controlSeleccionMenu, Alumno alumno) {
        this.controlSeleccionMenu = controlSeleccionMenu;
        inicia(total, List.of("Menú Semanal"), alumno);
    }

    /**
     * Método genérico que maneja la lógica principal.
     * @param total El monto total a pagar.
     * @param conceptos La lista de conceptos que se están pagando.
     * @param alumno El alumno para quien es el pago.
     */
    public void inicia(int total, List<String> conceptos, Alumno alumno) {
        this.total = total;
        this.conceptosAPagar = conceptos;
        this.alumno = alumno;
        ventana.muestra(this, total, conceptosAPagar);
    }

    /**
     * Inicia el flujo de pago en línea.
     */
    public void irAPagoLinea() {
        ventana.cerrar();

        controlDatosPagoEnLinea.inicia(
                total,
                () -> controlPagoEnLinea.inicia(total, this.alumno),
                this.alumno
        );
    }

    /**
     * Inicia el flujo de pago en caja.
     */
    public void irAPagoCaja() {
        ventana.cerrar();
        // CORRECCIÓN: Se añade 'this.alumno' como segundo argumento para que coincida
        // con la firma del método 'inicia' en ControlPagoCaja.
        controlPagoCaja.inicia(total, this.alumno);
    }

    public void regresar() {
        ventana.cerrar();
        if (controlSeleccionMenu != null) {
            // Suponiendo que el control tiene un método para mostrarse de nuevo
            // controlSeleccionMenu.muestra();
        }
    }
}