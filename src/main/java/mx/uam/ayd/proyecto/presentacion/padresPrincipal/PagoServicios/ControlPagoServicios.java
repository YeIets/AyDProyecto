package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa la entidad Alumno
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoServicios {

    @Autowired
    private VentanaPagoServicios ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    // Campo para guardar el alumno que está usando este módulo
    private Alumno alumno;

    /**
     * CAMBIO: El método ahora recibe al Alumno.
     * @param alumno El alumno para el cual se gestionan los pagos.
     */
    public void inicia(Alumno alumno) {
        this.alumno = alumno; // Se guarda el alumno
        ventana.muestra(this);
    }

    /**
     * Es llamado por la ventana cuando el usuario da clic en "Pagar".
     * CAMBIO: Ahora pasa el alumno al siguiente controlador.
     * @param total El total calculado de los checkboxes seleccionados.
     */
    public void procederAlPago(int total) {
        if (total > 0) {
            ventana.cerrar();
            // Se pasa el total y el alumno al siguiente paso del flujo
            controlSeleccionPagoMenu.inicia(total, null, this.alumno);
        } else {
            System.out.println("Por favor, seleccione al menos un concepto a pagar.");
            // Aquí podrías usar una ventana de alerta
        }
    }
}