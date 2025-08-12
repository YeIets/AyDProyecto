package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu;

import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;

import java.util.List;

@Component
public class ControlSeleccionMenu {

    private Padre padreSesion;
    @Autowired
    private VentanaSeleccionMenu ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    /**
     * Inicia la ventana de selección de menú
     */
    public void inicia(Padre padre) {
        padreSesion = padre;
        ventana.muestra(this);
    }

    /**
     * Llama a la ventana de pagos, pasando el total y referencia a este controlador
     */
    public void irAVentanaPagos(int total, List<String> diasSeleccionados) {
        ventana.cerrar();
        controlSeleccionPagoMenu.inicia(total, this, diasSeleccionados, padreSesion);
    }

    /**
     * Método para volver a mostrar esta ventana (por ejemplo, desde la de pago)
     */
    public void regresar() {
        inicia(padreSesion);
    }
}
