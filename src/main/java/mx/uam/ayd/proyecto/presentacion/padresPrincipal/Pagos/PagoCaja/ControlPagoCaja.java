package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoCaja {

    @Autowired
    private VentanaPagoCaja ventana;

    /**
     * Inicia la visualización de la ventana de pago en caja.
     *
     * @param total el monto total a pagar que se mostrará en la ventana.
     */
    public void inicia(int total) {
        // Le pasa el control a la ventana para que se muestre,
        // enviando una referencia a sí mismo (this) y el total a pagar.
        ventana.muestra(this, total);
    }
}