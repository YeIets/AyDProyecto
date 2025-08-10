package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios;


import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoServicios {

    @Autowired
    private VentanaPagoServicios ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    /**
     * Muestra la ventana para seleccionar los pagos a realizar.
     */
    public void inicia() {
        ventana.muestra(this);
    }

    /**
     * Es llamado por la ventana cuando el usuario da clic en "Pagar".
     * Inicia el siguiente flujo: la elección del método de pago.
     * @param total El total calculado de los checkboxes seleccionados.
     */
    public void procederAlPago(int total) {
        if (total > 0) {
            ventana.cerrar();
            // Inicia la siguiente ventana, pasándole el total calculado.
            // El segundo parámetro es null porque desde este flujo no venimos del menú semanal.
            controlSeleccionPagoMenu.inicia(total, null);
        } else {
            // Aquí se podría mostrar una alerta si el total es cero.
            System.out.println("Por favor, seleccione al menos un concepto a pagar.");
        }
    }
}