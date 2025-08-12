package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author      Nombre del autor(es)
 * @since       1.2
 *
 * Módulo de control para la gestión del pago de servicios.
 */
@Component
public class ControlPagoServicios {

    @Autowired
    private VentanaPagoServicios ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    // CAMBIO: Se añade una variable para almacenar el alumno del contexto.
    private Alumno alumno;

    /**
     * CAMBIO: El método 'inicia' ahora requiere el alumno para el cual se realiza el pago.
     * @param alumno El alumno asociado a la sesión de pago.
     */
    public void inicia(Alumno alumno) {
        this.alumno = alumno;
        ventana.muestra(this);
    }

    /**
     * Procede al siguiente paso del flujo de pago.
     *
     * @param total El monto total calculado.
     * @param conceptos La lista de nombres de los servicios seleccionados.
     */
    public void procederAlPago(int total, List<String> conceptos) {
        if (total > 0) {
            ventana.cerrar();
            // CAMBIO: Se pasa el objeto 'alumno' al siguiente controlador.
            controlSeleccionPagoMenu.inicia(total, conceptos, this.alumno);
        } else {
            ventana.muestraDialogoDeAdvertencia("Por favor, seleccione al menos un concepto a pagar.");
        }
    }
}