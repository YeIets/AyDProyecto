package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;

@Component
public class ControlSeleccionMenu {

    @Autowired
    private VentanaSeleccionMenu ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    /**
     * Inicia la ventana de selección de menú
     */
    public void inicia() {
        ventana.muestra(this);
    }

    /**
     * Llama a la ventana de pagos, pasando el total y referencia a este controlador
     */
    public void irAVentanaPagos(int total) {
        ventana.cerrar();
        controlSeleccionPagoMenu.inicia(total, this);
    }

    /**
     * Método para volver a mostrar esta ventana (por ejemplo, desde la de pago)
     */
    public void regresar() {
        inicia();
    }
}
