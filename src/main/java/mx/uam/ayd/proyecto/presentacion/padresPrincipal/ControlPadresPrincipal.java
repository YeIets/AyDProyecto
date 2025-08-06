package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.padreMenuSemanal.ControlPadreMenuSemanal;

@Component
public class ControlPadresPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlPadresPrincipal.class);

    private final VentanaPadresPrincipal ventana;

    @Autowired
    private ControlPadreMenuSemanal controlPadreMenuSemanal;

    @Autowired
    public ControlPadresPrincipal(VentanaPadresPrincipal ventana) {
        this.ventana = ventana;
    }

    @PostConstruct
    public void init() {
        ventana.setControlPadresPrincipal(this);
    }

    public void inicia() {
        ventana.muestra();
    }

    // Método que será llamado al presionar el botón "Menú Semanal"
    public void irAMenuSemanal() {
        controlPadreMenuSemanal.inicia();
    }
}
