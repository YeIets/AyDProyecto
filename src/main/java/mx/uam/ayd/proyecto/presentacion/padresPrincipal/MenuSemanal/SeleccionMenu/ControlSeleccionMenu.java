package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa la entidad Alumno
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;

@Component
public class ControlSeleccionMenu {

    @Autowired
    private VentanaSeleccionMenu ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    // Campo para guardar el alumno que está usando este módulo
    private Alumno alumno;

    /**
     * CAMBIO: Inicia la ventana de selección de menú para un alumno específico.
     * @param alumno El alumno para el cual se seleccionan los días del menú.
     */
    public void inicia(Alumno alumno) {
        this.alumno = alumno; // Se guarda el alumno
        ventana.muestra(this, alumno);
    }

    /**
     * CAMBIO: Pasa el total y el alumno a la ventana de pagos.
     * @param total El monto total de los días de menú seleccionados.
     */
    public void irAVentanaPagos(int total) {
        ventana.cerrar();
        // Se pasa el total, la referencia a este controlador y el alumno actual
        controlSeleccionPagoMenu.inicia(total, this, this.alumno);
    }

    /**
     * Método para volver a mostrar esta ventana (por ejemplo, desde la de pago).
     */
    public void regresar() {
        // Se vuelve a iniciar con el mismo alumno
        inicia(this.alumno);
    }
}