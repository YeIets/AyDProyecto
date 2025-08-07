package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoMenuLinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadresPagosElegir.ControlPadresPagosElegir;

@Component
public class ControlPagoLineaMenu {

    @Autowired
    private VentanaPagoLineaMenu ventana;

    @Autowired
    private ControlPadresPagosElegir controlPadresPagosElegir;

    /**
     * Inicia la ventana de pago en línea
     * @param total El total que se va a mostrar en la vista
     */
    public void inicia(int total) {
        ventana.muestra(this, total);
    }

    /**
     * Regresa a la ventana anterior (pago elegir)
     */
    public void regresar() {
        ventana.cerrar();
        controlPadresPagosElegir.regresar();
    }
}
