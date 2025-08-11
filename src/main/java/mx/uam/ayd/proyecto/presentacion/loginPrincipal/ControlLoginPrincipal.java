package mx.uam.ayd.proyecto.presentacion.loginPrincipal;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.ServicioAdministrativo;
import mx.uam.ayd.proyecto.negocio.ServicioEncargadoCocina;
import mx.uam.ayd.proyecto.negocio.ServicioPadre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ControlPadresPrincipal;
import mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.ControlAdministrativoPrincipal;
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.ControlEncargadoCocinaPrincipal;

@Component
public class ControlLoginPrincipal {

    // Se inyectan los controladores de las ventanas principales
    private final ControlPadresPrincipal controlPadresPrincipal;
    private final ControlAdministrativoPrincipal controlAdministrativoPrincipal;
    private final ControlEncargadoCocinaPrincipal controlEncargadoCocinaPrincipal;
    private final VentanaLoginPrincipal ventana;

    // Se inyectan los servicios necesarios para la lógica de negocio de autenticación
    private final ServicioPadre servicioPadre;
    private final ServicioAdministrativo servicioAdministrativo;
    private final ServicioEncargadoCocina servicioEncargadoCocina;

    @Autowired
    public ControlLoginPrincipal(
            VentanaLoginPrincipal ventana,
            ControlPadresPrincipal controlPadresPrincipal,
            ControlAdministrativoPrincipal controlAdministrativoPrincipal,
            ControlEncargadoCocinaPrincipal controlEncargadoCocinaPrincipal,
            ServicioPadre servicioPadre,
            ServicioAdministrativo servicioAdministrativo,
            ServicioEncargadoCocina servicioEncargadoCocina
    ) {
        this.ventana = ventana;
        this.controlPadresPrincipal = controlPadresPrincipal;
        this.controlAdministrativoPrincipal = controlAdministrativoPrincipal;
        this.controlEncargadoCocinaPrincipal = controlEncargadoCocinaPrincipal;
        this.servicioPadre = servicioPadre;
        this.servicioAdministrativo = servicioAdministrativo;
        this.servicioEncargadoCocina = servicioEncargadoCocina;
    }

    @PostConstruct
    public void init() {
        if (this.ventana != null) {
            this.ventana.setControlLoginPrincipal(this);
        }
    }

    public void inicia() {
        ventana.muestra();
    }

    public void padresPrincipal() {
        ventana.cerrar();
        controlPadresPrincipal.inicia();
    }

    public void administrativoPrincipal() {
        ventana.cerrar();
        controlAdministrativoPrincipal.inicia();
    }

    public void encargadoCocinaPrincipal() {
        ventana.cerrar();
        controlEncargadoCocinaPrincipal.inicia();
    }

    /**
     * Verifica las credenciales de un Padre directamente a través del ServicioPadre.
     * @param correo El correo del padre a verificar.
     * @param password La contraseña a verificar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean verificarPadreRegistrado(String correo, String password) {
        // Se invoca directamente al servicio para la verificación.
        return servicioPadre.verificarPadreRegistrado(correo, password) != null;
    }

    /**
     * Verifica las credenciales de un Administrador directamente a través del ServicioAdministrativo.
     * @param correo El correo del administrador a verificar.
     * @param password La contraseña a verificar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean verificarAdministradorRegistrado(String correo, String password) {
        // Se invoca directamente al servicio para la verificación.
        return servicioAdministrativo.verificarAdministrativoRegistrado(correo, password) != null;
    }

    /**
     * Verifica las credenciales de un Encargado de Cocina directamente a través del ServicioEncargadoCocina.
     * @param correo El correo del encargado a verificar.
     * @param password La contraseña a verificar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean verificarEncargadoDeCocinaRegistrado(String correo, String password) {
        // Se invoca directamente al servicio para la verificación.
        return servicioEncargadoCocina.verificarEncargadoDeCocinaRegistrado(correo, password) != null;
    }
}