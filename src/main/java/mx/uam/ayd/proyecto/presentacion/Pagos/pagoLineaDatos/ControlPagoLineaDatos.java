package mx.uam.ayd.proyecto.presentacion.Pagos.pagoLineaDatos; // O la ruta que le corresponda

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPagoLineaDatos {

    @Autowired
    private VentanaPagoLineaDatos ventana;

    private Runnable accionExito;

    /**
     * CORRECCIÓN AQUÍ:
     * El método ahora acepta los dos parámetros que le estás enviando.
     * @param total El monto a mostrar en la ventana de pago.
     * @param accionAlTerminar La tarea a ejecutar después de un pago exitoso.
     */
    public void inicia(int total, Runnable accionAlTerminar) {
        this.accionExito = accionAlTerminar;
        ventana.muestra(this, total); // Pasamos el total a la ventana
    }

    /**
     * Simula el procesamiento del pago y ejecuta la acción de éxito.
     */
    public void procesarPago() {
        // Lógica para validar y procesar el pago...
        System.out.println("Procesando pago...");

        // Suponiendo que el pago fue exitoso:
        ventana.muestraDialogoDeExito();
        ventana.cerrar();

        // Ejecutamos la "instrucción" que nos pasaron
        if (accionExito != null) {
            accionExito.run();
        }
    }

    /**
     * Método llamado cuando el usuario presiona "Regresar".
     */
    public void regresar() {
        ventana.cerrar();
    }
}