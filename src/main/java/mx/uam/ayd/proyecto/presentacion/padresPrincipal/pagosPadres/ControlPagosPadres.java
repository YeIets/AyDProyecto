package mx.uam.ayd.proyecto.presentacion.padresPrincipal.pagosPadres;


import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadresPagosElegir.ControlPadresPagosElegir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagosPadres {

    @Autowired
    private VentanaPagosPadres ventana;

    @Autowired
    private ControlPadresPagosElegir controlPadresPagosElegir;

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
            controlPadresPagosElegir.inicia(total, null);
        } else {
            // Aquí se podría mostrar una alerta si el total es cero.
            System.out.println("Por favor, seleccione al menos un concepto a pagar.");
        }
    }
}