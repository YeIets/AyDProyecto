package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja.ControlPagoCaja;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea.ControlDatosPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea.ControlPagoEnLinea;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;

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

    private ControlSeleccionMenu controlSeleccionMenu;
    private int total;
    private Alumno alumno;

    /**
     * Inicia el flujo para seleccionar el método de pago.
     * @param total El monto a pagar.
     * @param controlSeleccionMenu El controlador anterior, para poder regresar.
     * @param alumno El alumno para el cual se realiza el pago.
     */
    public void inicia(int total, ControlSeleccionMenu controlSeleccionMenu, Alumno alumno) {
        this.total = total;
        this.controlSeleccionMenu = controlSeleccionMenu;
        this.alumno = alumno;
        ventana.muestra(this, total, this.alumno);
    }

    /**
     * Inicia el flujo de pago en línea.
     */
    public void irAPagoLinea() {
        ventana.cerrar();

        // CAMBIO AQUÍ: Se añade 'this.alumno' como tercer argumento a la llamada.
        controlDatosPagoEnLinea.inicia(total, () -> controlPagoEnLinea.inicia(total, this.alumno), this.alumno);
    }

    /**
     * Inicia el flujo de pago en caja.
     */
    public void irAPagoCaja() {
        ventana.cerrar();
        controlPagoCaja.inicia(total, this.alumno);
    }

    /**
     * Regresa a la ventana anterior.
     */
    public void regresar() {
        ventana.cerrar();
        if (this.controlSeleccionMenu != null) {
            this.controlSeleccionMenu.regresar();
        }
    }
}