package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// <-- 1. IMPORTAMOS LOS NUEVOS CONTROLES
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadreMenuSemanal.ControlPadreMenuSemanal;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos.ControlActualizarDocumentos;

@Component
public class ControlPadresPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlPadresPrincipal.class);

    private final VentanaPadresPrincipal ventana;

    @Autowired
    private ControlPadreMenuSemanal controlPadreMenuSemanal;

    // <-- 2. INYECTAMOS EL NUEVO CONTROLADOR
    @Autowired
    private ControlActualizarDocumentos controlActualizarDocumentos;

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

    // <-- 3. CREAMOS EL MÉTODO DE NAVEGACIÓN
    /**
     * Inicia el flujo de la HU-05 para subir documentos
     */
    public void irAActualizarDocumentos() {
        controlActualizarDocumentos.inicia();
    }

    // Método que será llamado al presionar el botón "Menú Semanal"
    public void irAMenuSemanal() {
        controlPadreMenuSemanal.inicia();
    }
}