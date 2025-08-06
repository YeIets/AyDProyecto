package mx.uam.ayd.proyecto.presentacion.seleccionMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlSeleccionMenu {

    @Autowired
    private VentanaSeleccionMenu ventana;

    /**
     * Inicia la ventana de selección de menú
     */
    public void inicia() {
        ventana.muestra(this);
    }

    /**
     * Este método es llamado cada vez que se selecciona o deselecciona un día.
     * Calcula el total y actualiza el Label correspondiente.
     */
    public void actualizarTotal(boolean lunes, boolean martes, boolean miercoles, boolean jueves, boolean viernes) {
        int total = 0;
        int costoPorDia = 35;

        if (lunes) total += costoPorDia;
        if (martes) total += costoPorDia;
        if (miercoles) total += costoPorDia;
        if (jueves) total += costoPorDia;
        if (viernes) total += costoPorDia;

        ventana.actualizaTotal("$" + total);
    }

    /**
     * Método ejecutado cuando se presiona el botón "Pagar"
     */
    public void pagar() {
        // En una versión real se conectaría con el servicio de pagos.
        // Por ahora simplemente cierra la ventana.
        ventana.cerrar();
        System.out.println("Pago procesado correctamente.");
    }

    /**
     * Método ejecutado cuando se presiona el botón "Volver"
     */
    public void volverAlMenu() {
        ventana.cerrar();

        // Aquí deberías redirigir a la ventana anterior, por ejemplo:
        // controlPadreMenuSemanal.inicia();
    }
}
