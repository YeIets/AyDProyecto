package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa Alumno
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoCaja {

    @Autowired
    private VentanaPagoCaja ventana;

    private Alumno alumno; // Campo para el alumno

    /**
     * CAMBIO: Inicia la ventana de pago en caja para un alumno específico.
     * @param total El monto a pagar.
     * @param alumno El alumno que realiza el pago.
     */
    public void inicia(int total, Alumno alumno) {
        this.alumno = alumno; // Se guarda el alumno
        ventana.muestra(this, total, this.alumno);
    }
}