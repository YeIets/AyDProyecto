package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import mx.uam.ayd.proyecto.negocio.ServicioNotificacion;
import mx.uam.ayd.proyecto.negocio.ServicioPadre;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Importar la clase Alumno
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos.ControlActualizarDocumentos;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.ControlMenuSemanal;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.ControlPagoServicios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPadresPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlPadresPrincipal.class);

    @Autowired
    private VentanaPadresPrincipal ventana;

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

    // Este campo guardará la información del padre que inició sesión
    @Setter
    private Padre padreSesion;

    @PostConstruct
    public void init() {
        ventana.setControlPadresPrincipal(this);
    }

    public void inicia() {
        // Simulación: Asignamos un padre a la sesión para pruebas.
        // En tu flujo real, esto lo harías después del login.
        this.padreSesion = servicioPadre.recuperaPadrePorCorreo("Juan@uam.com");
        ventana.muestra();
    }

    /**
     * CAMBIO: Ahora obtiene al hijo y se lo pasa al siguiente controlador.
     */
    public void irAActualizarDocumentos() {
        Alumno hijo = obtenerHijoSeleccionado();
        if (hijo != null) {
            controlActualizarDocumentos.inicia(hijo);
        }
    }

    /**
     * CAMBIO: Ahora obtiene al hijo y se lo pasa al siguiente controlador.
     */
    public void irAMenuSemanal() {
        Alumno hijo = obtenerHijoSeleccionado();
        if (hijo != null) {
            controlMenuSemanal.inicia(hijo);
        }
    }

    /**
     * CAMBIO: Ahora obtiene al hijo y se lo pasa al siguiente controlador.
     */
    public void irAHacerPagos() {
        Alumno hijo = obtenerHijoSeleccionado();
        if (hijo != null) {
            controlPagoServicios.inicia(hijo);
        }
    }

    public boolean padreTieneNotificaciones() {
        if (padreSesion == null) {
            return false;
        }
        return servicioNotificacion.tieneNotificaciones(padreSesion);
    }

    /**
     * Método de ayuda para obtener el hijo.
     * Por ahora, selecciona al primer hijo de la lista.
     * @return El Alumno seleccionado o null si no hay.
     */
    private Alumno obtenerHijoSeleccionado() {
        if (padreSesion != null && !padreSesion.getHijos().isEmpty()) {
            // Se retorna el primer hijo de la lista
            return padreSesion.getHijos().get(0);
        } else {
            log.warn("El padre de la sesión no tiene hijos asignados.");
            ventana.muestraDialogoDeError("No hay un hijo asociado a esta cuenta para realizar la operación.");
            return null;
        }
    }
}