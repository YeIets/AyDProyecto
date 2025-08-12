package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios;


import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.SeleccionPagoServicios.ControlSeleccionPagoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControlPagoServicios {

    private Padre padreSesion;
    @Autowired
    private VentanaPagoServicios ventana;

    @Autowired
    private final ControlSeleccionPagoServicios controlSeleccionPagoServicios;

    public ControlPagoServicios(ControlSeleccionPagoServicios controlSeleccionPagoServicios) {
        this.controlSeleccionPagoServicios = controlSeleccionPagoServicios;
    }

    /**
     * Muestra la ventana para seleccionar los pagos a realizar.
     */
    public void inicia(Padre padre) {
        ventana.muestra(this);
        this.padreSesion = padre;
    }

    /**
     * Es llamado por la ventana cuando el usuario da clic en "Pagar".
     * Inicia el siguiente flujo: la elección del método de pago.
     * @param total El total calculado de los checkboxes seleccionados.
     */
    public void procederAlPago(int total, List<String> servicios) {
        if (total > 0) {
            ventana.cerrar();
            // Inicia la siguiente ventana, pasándole el total calculado.
            // El segundo parámetro es null porque desde este flujo no venimos del menú semanal.
            controlSeleccionPagoServicios.inicia(total, null, servicios,padreSesion);
        } else {
            // Aquí se podría mostrar una alerta si el total es cero.
            System.out.println("Por favor, seleccione al menos un concepto a pagar.");
        }
    }
}