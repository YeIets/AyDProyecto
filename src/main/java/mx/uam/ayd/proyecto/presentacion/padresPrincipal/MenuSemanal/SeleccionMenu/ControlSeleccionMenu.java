package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;

@Component
public class ControlSeleccionMenu {

    @Autowired
    private VentanaSeleccionMenu ventana;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    private Alumno alumno;

    public void inicia(Alumno alumno) {
        this.alumno = alumno;
        ventana.muestra(this, this.alumno);
    }

    public void irAVentanaPagos(int total) {
        ventana.cerrar();
        controlSeleccionPagoMenu.inicia(total, this, this.alumno);
    }

    /**
     * Este método se encarga de la lógica de "regresar".
     * Vuelve a iniciar esta misma ventana.
     */
    public void regresar() {
        // Podrías cerrar la ventana actual antes si es necesario
        // ventana.cerrar();
        // Por ahora, simplemente vuelve a mostrar la ventana, lo que la trae al frente
        // o la recrea si fue cerrada.
        inicia(this.alumno);
    }
}