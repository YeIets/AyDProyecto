package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlDatosPagoEnLinea {

    @Autowired
    private VentanaDatosPagoEnLinea ventana;

    private Runnable accionExito;

    public void inicia(int total, Runnable accionAlTerminar) {
        this.accionExito = accionAlTerminar;
        ventana.muestra(this, total);
    }

    /**
     * Nuevo método que se encarga de validar los datos de la ventana.
     */
    public void validarYProcesarPago() {
        // PASO 1: Obtener los datos de la ventana
        String numeroTarjeta = ventana.getNumeroTarjeta();
        String fecha = ventana.getFechaVencimiento();
        String cvv = ventana.getCVV();

        // PASO 2: Realizar las validaciones una por una

        // Validación 1: Campos vacíos
        if (numeroTarjeta.isBlank() || fecha.isBlank() || cvv.isBlank()) {
            ventana.muestraDialogoDeError("Todos los campos son obligatorios.");
            return; // Detiene la ejecución si hay un error
        }

        // Validación 2: Número de tarjeta (16 dígitos numéricos)
        if (!numeroTarjeta.matches("\\d+") || numeroTarjeta.length() != 16) {
            ventana.muestraDialogoDeError("El número de tarjeta debe contener exactamente 16 dígitos numéricos.");
            return;
        }

        // Validación 3: Fecha de vencimiento (formato MM/AA)
        if (!fecha.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            ventana.muestraDialogoDeError("La fecha de vencimiento debe tener el formato MM/AA (por ejemplo, 08/29).");
            return;
        }

        // Validación 4: CVV (3 dígitos numéricos)
        if (!cvv.matches("\\d+") || cvv.length() != 3) {
            ventana.muestraDialogoDeError("El CVV debe contener exactamente 3 dígitos numéricos.");
            return;
        }

        // PASO 3: Si todas las validaciones pasan, se procesa el pago
        procesarPago();
    }

    /**
     * Simula el procesamiento del pago y ejecuta la acción de éxito.
     */
    private void procesarPago() {
        System.out.println("Validaciones correctas. Procesando pago...");

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