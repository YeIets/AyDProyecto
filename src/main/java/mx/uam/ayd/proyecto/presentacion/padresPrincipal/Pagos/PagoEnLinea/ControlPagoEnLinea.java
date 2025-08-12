package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa Alumno
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoEnLinea {

    @Autowired
    private VentanaPagoEnLinea ventana;

    private Alumno alumno; // Campo para el alumno

    /**
     * CAMBIO: Muestra el ticket de pago para un alumno específico.
     * @param total El total pagado.
     * @param alumno El alumno que realizó el pago.
     */
    public void inicia(int total, Alumno alumno) {
        this.alumno = alumno; // Se guarda el alumno
        ventana.muestra(this, total, this.alumno);
    }
}