package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.PublicarMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.ControlEncargadoCocinaPrincipal;

@Component
public class ControlPublicarMenu {

    private static final Logger log = LoggerFactory.getLogger(ControlPublicarMenu.class);

    @Autowired
    private VentanaPublicarMenu ventana;
    private ControlEncargadoCocinaPrincipal controlEncargadoCocinaPrincipal;

    /**
     * Inicia el flujo para mostrar la ventana de publicación de menú
     * @param controlPrincipal El control de la ventana principal para poder regresar a ella.
     */
    public void inicia(ControlEncargadoCocinaPrincipal controlPrincipal) {
        log.info("Iniciando ControlPublicarMenu");
        this.controlEncargadoCocinaPrincipal = controlPrincipal;
        ventana.muestra(this);
    }

    /**
     * Cierra la ventana actual y regresa al menú principal del encargado de cocina.
     */
    public void regresar() {
        log.info("Regresando desde la ventana de Publicar Menú");
        ventana.cerrar();
        // Lógica para regresar a la ventana principal.
        if (controlEncargadoCocinaPrincipal != null) {
            controlEncargadoCocinaPrincipal.inicia();
        }
    }
}

