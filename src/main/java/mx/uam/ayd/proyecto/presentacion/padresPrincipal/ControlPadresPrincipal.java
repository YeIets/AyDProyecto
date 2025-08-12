package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.negocio.ServicioPadre;
import mx.uam.ayd.proyecto.negocio.ServicioNotificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.ControlMenuSemanal;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos.ControlActualizarDocumentos;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.ControlPagoServicios;

import java.util.Optional;


@Component
public class ControlPadresPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlPadresPrincipal.class);

    private final VentanaPadresPrincipal ventana;

    @Setter
    private Padre padreSesion;
    @Autowired
    private ServicioPadre servicioPadre;

    @Autowired
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    private ControlMenuSemanal controlMenuSemanal;

    @Autowired
    private ControlActualizarDocumentos controlActualizarDocumentos;

    @Autowired
    private ControlPagoServicios controlPagoServicios;

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

    //Abre la ventana de Actualizar Documentos
    public void irAActualizarDocumentos() {
        controlActualizarDocumentos.inicia();
    }

    //Abre la ventana de Menu Semanal
    public void irAMenuSemanal() {
        controlMenuSemanal.inicia(padreSesion);
    }

    //Abre la ventana de Hacer Pagos
    public void irAHacerPagos() {
        controlPagoServicios.inicia(padreSesion);
    }

    public Padre recuperarPadre(String correo) {
        return servicioPadre.recuperaPadrePorCorreo(correo);
    }

    public boolean padreTieneNotificaciones() {
    if (padreSesion == null) {
            return false;
        }
        return servicioNotificacion.tieneNotificaciones(padreSesion);
    }
}