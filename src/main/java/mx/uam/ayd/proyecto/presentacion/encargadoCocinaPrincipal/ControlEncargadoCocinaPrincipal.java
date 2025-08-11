package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.negocio.ServicioEncargadoCocina;
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.PublicarMenu.ControlPublicarMenu;

@Component
public class ControlEncargadoCocinaPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlEncargadoCocinaPrincipal.class);

    @Autowired
    private VentanaEncargadoCocinaPrincipal ventana;

    @Autowired
    private ServicioEncargadoCocina servicioEncargadoCocina;

    // Se agrega la inyección del control para la ventana de publicar menú
    @Autowired
    private ControlPublicarMenu controlPublicarMenu;

    /**
     * Se mantiene el constructor para la inyección de la ventana.
     * @param ventana La vista gestionada por este controlador.
     */
    @Autowired
    public ControlEncargadoCocinaPrincipal(VentanaEncargadoCocinaPrincipal ventana) {
        this.ventana = ventana;
    }

    /**
     * Método que se ejecuta después de la construcción del bean
     * y realiza la conexión bidireccional entre el control y la ventana.
     */
    @PostConstruct
    public void init() {
        ventana.setControlEncargadoCocinaPrincipal(this);
    }

    /**
     * Inicia el caso de uso mostrando la ventana principal del encargado de cocina.
     */
    public void inicia() {
        ventana.muestra();
    }

    /**
     * Método para iniciar el flujo de publicación de menú.
     * Cierra la ventana principal y abre la de Publicar Menú.
     */
    public void muestraPublicarMenu() {
        log.info("Iniciando flujo para mostrar la ventana de Publicar Menu");
        ventana.oculta();
        controlPublicarMenu.inicia(this);
    }
}
