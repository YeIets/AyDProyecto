package mx.uam.ayd.proyecto.presentacion.Pagos.pagoEnLinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoLinea {

    @Autowired
    private VentanaPagoLinea ventana;

    /**
     * Este es el controlador del "ticket" final. Su única función
     * es recibir el total y decirle a la ventana que se muestre.
     * @param total El total a mostrar en el ticket.
     */
    public void inicia(int total) {
        ventana.muestra(this, total);
    }

    // El método procesarPago() se elimina porque esta ventana no procesa pagos, solo los muestra.
}