package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Nota: El 'ServicioEncargadoCocina' ya no es necesario aquí si este controlador
// no tiene lógica de negocio propia, pero se puede mantener por si se añade en el futuro.
import mx.uam.ayd.proyecto.negocio.ServicioEncargadoCocina;

@Component
public class ControlEncargadoCocinaPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlEncargadoCocinaPrincipal.class);

    @Autowired
    private VentanaEncargadoCocinaPrincipal ventana;

    @Autowired
    private ServicioEncargadoCocina servicioEncargadoCocina;

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

    // Los métodos 'agregarEncargadoCocina' y 'verificarEncargadoDeCocinaRegistrado'
    // han sido eliminados para resolver los errores de compilación y alinear
    // la clase con su responsabilidad única de controlar la ventana principal.
}