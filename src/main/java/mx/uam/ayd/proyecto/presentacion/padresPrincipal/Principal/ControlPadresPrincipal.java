package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Principal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// --- IMPORTAMOS LOS CONTROLES ---
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadreMenuSemanal.ControlPadreMenuSemanal;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos.ControlActualizarDocumentos;
// 1. IMPORTAR el nuevo controlador de pagos
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.pagosPadres.ControlPagosPadres;


@Component
public class ControlPadresPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlPadresPrincipal.class);

    private final VentanaPadresPrincipal ventana;

    @Autowired
    private ControlPadreMenuSemanal controlPadreMenuSemanal;

    @Autowired
    private ControlActualizarDocumentos controlActualizarDocumentos;

    // 2. INYECTAR el nuevo controlador de pagos
    @Autowired
    private ControlPagosPadres controlPagosPadres;

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

    public void irAActualizarDocumentos() {
        controlActualizarDocumentos.inicia();
    }

    public void irAMenuSemanal() {
        controlPadreMenuSemanal.inicia();
    }

    // 3. CREAR MÉTODO de navegación para los pagos
    /**
     * Inicia el flujo para realizar pagos (seleccionar conceptos)
     */
    public void irAHacerPagos() {
        controlPagosPadres.inicia();
    }
}