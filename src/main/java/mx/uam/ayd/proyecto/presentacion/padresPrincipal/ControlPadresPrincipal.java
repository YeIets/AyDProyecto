package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.negocio.ServicioPadre;
import mx.uam.ayd.proyecto.negocio.ServicioNotificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // CAMBIO: Se importa la clase Alumno
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.ControlMenuSemanal;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos.ControlActualizarDocumentos;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.ControlPagoServicios;


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

    /**
     * CAMBIO: Se obtiene el alumno del padre en sesión y se pasa al siguiente controlador.
     */
    public void irAActualizarDocumentos() {
        if (padreSesion == null || padreSesion.getAlumno() == null) {
            ventana.muestraDialogoDeError("No se ha podido identificar al alumno. Por favor, inicie sesión nuevamente.");
            return;
        }
        Alumno alumno = padreSesion.getAlumno();
        controlActualizarDocumentos.inicia(alumno);
    }

    /**
     * CAMBIO: Se obtiene el alumno del padre en sesión y se pasa al siguiente controlador.
     */
    public void irAMenuSemanal() {
        if (padreSesion == null || padreSesion.getAlumno() == null) {
            ventana.muestraDialogoDeError("No se ha podido identificar al alumno. Por favor, inicie sesión nuevamente.");
            return;
        }
        Alumno alumno = padreSesion.getAlumno();
        controlMenuSemanal.inicia(alumno);
    }

    /**
     * CAMBIO: Se obtiene el alumno del padre en sesión y se pasa al siguiente controlador.
     */
    public void irAHacerPagos() {
        if (padreSesion == null || padreSesion.getAlumno() == null) {
            ventana.muestraDialogoDeError("No se ha podido identificar al alumno. Por favor, inicie sesión nuevamente.");
            return;
        }
        Alumno alumno = padreSesion.getAlumno();
        controlPagoServicios.inicia(alumno);
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