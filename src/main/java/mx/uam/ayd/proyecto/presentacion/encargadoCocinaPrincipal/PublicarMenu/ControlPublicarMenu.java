package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.PublicarMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.negocio.ServicioMenu; // CAMBIO: Se importa el servicio
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.ControlEncargadoCocinaPrincipal;

@Component
public class ControlPublicarMenu {

    private static final Logger log = LoggerFactory.getLogger(ControlPublicarMenu.class);

    @Autowired
    private VentanaPublicarMenu ventana;

    // CAMBIO: Se inyecta el servicio para poder usarlo
    @Autowired
    private ServicioMenu servicioMenu;

    private ControlEncargadoCocinaPrincipal controlEncargadoCocinaPrincipal;

    public void inicia(ControlEncargadoCocinaPrincipal controlPrincipal) {
        log.info("Iniciando ControlPublicarMenu");
        this.controlEncargadoCocinaPrincipal = controlPrincipal;
        ventana.muestra(this);
    }

    /**
     * ✅ NUEVO MÉTODO: Recibe los datos de la ventana y los pasa al servicio para actualizarlos.
     */
    public void publicarMenu(String dia, String platillo, String bebida, String fruta, String postre) {
        // Validación básica para no guardar datos vacíos
        if (!platillo.isEmpty() && !bebida.isEmpty() && !fruta.isEmpty() && !postre.isEmpty()) {
            log.info("Publicando menú para {}: {}, {}, {}, {}", dia, platillo, bebida, fruta, postre);
            servicioMenu.actualizarPlatilloDelDia(dia, platillo, bebida, fruta, postre);
        } else {
            log.warn("Se intentó publicar un menú incompleto para el día {}", dia);
        }
    }

    public void regresar() {
        log.info("Regresando desde la ventana de Publicar Menú");
        ventana.cerrar();
        if (controlEncargadoCocinaPrincipal != null) {
            controlEncargadoCocinaPrincipal.inicia();
        }
    }
}