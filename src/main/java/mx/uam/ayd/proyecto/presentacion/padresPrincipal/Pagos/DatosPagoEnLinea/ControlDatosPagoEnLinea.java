package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa Alumno
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlDatosPagoEnLinea {

    @Autowired
    private VentanaDatosPagoEnLinea ventana;

    private Runnable accionExito;
    private Alumno alumno; // Campo para el alumno

    /**
     * CAMBIO: El método ahora acepta al Alumno.
     * @param total El monto a pagar.
     * @param accionAlTerminar La tarea a ejecutar después de un pago exitoso.
     * @param alumno El alumno que realiza el pago.
     */
    public void inicia(int total, Runnable accionAlTerminar, Alumno alumno) {
        this.accionExito = accionAlTerminar;
        this.alumno = alumno; // Se guarda el alumno
        ventana.muestra(this, total, this.alumno);
    }

    public void validarYProcesarPago() {
        // ... (la lógica de validación no cambia)

        // Si todas las validaciones pasan, se procesa el pago
        procesarPago();
    }

    private void procesarPago() {
        // Al procesar el pago, ahora tienes acceso a 'this.alumno'
        // para poder asociar el pago con el estudiante correcto en la base de datos.
        System.out.println("Procesando pago para el alumno: " + this.alumno.getNombreCompleto());

        ventana.muestraDialogoDeExito();
        ventana.cerrar();

        if (accionExito != null) {
            accionExito.run();
        }
    }

    public void regresar() {
        ventana.cerrar();
    }
}