package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.ServicioEncargadoCocina;
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.PublicarMenu.ControlPublicarMenu;
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.VerPedidos.ControlVerPedidos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlEncargadoCocinaPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlEncargadoCocinaPrincipal.class);

    @Autowired
    private VentanaEncargadoCocinaPrincipal ventana;

    @Autowired
    private ServicioEncargadoCocina servicioEncargadoCocina;

    @Autowired
    private ControlPublicarMenu controlPublicarMenu;

    @Autowired
    private ControlVerPedidos controlVerPedidos;

    public ControlEncargadoCocinaPrincipal(VentanaEncargadoCocinaPrincipal ventana) {
        this.ventana = ventana;
    }

    @PostConstruct
    public void init() {
        ventana.setControlEncargadoCocinaPrincipal(this);
    }

    public void inicia() {
        ventana.muestra();
    }

    public void muestraPublicarMenu() {
        log.info("Iniciando flujo para mostrar la ventana de Publicar Menu");
        ventana.oculta();
        controlPublicarMenu.inicia(this);
    }

    public void muestraVerPedidos() {
        log.info("Iniciando flujo para mostrar la ventana de Ver Pedidos");
        ventana.oculta();
        controlVerPedidos.inicia(this);
    }
}