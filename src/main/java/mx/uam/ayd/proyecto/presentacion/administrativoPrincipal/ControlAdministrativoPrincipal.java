package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioAdministrativo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos.ControlBuscarDocumentos;

@Component
public class ControlAdministrativoPrincipal {

    private static final Logger log = LoggerFactory.getLogger(ControlAdministrativoPrincipal.class);

    private final VentanaAdministrativoPrincipal ventana;

    @Autowired
    private ServicioAdministrativo servicioAdministrativo;

    @Autowired
    private ControlBuscarDocumentos controlBuscarDocumentos;

    @Autowired
    public ControlAdministrativoPrincipal(VentanaAdministrativoPrincipal ventana) {
        this.ventana = ventana;
    }

    @PostConstruct
    public void init() {
        ventana.setControlAdministrativoPrincipal(this);
    }

    public void inicia() {
        ventana.muestra();
    }

    /**
     * Inicia el flujo para buscar y gestionar documentos.
     */
    public void irABuscarDocumentos() {
        controlBuscarDocumentos.inicia();
    }

    //
    // Se ha eliminado el método 'agregarAdministrativo' porque la creación de usuarios
    // no es responsabilidad de este controlador, lo que soluciona el error.
    //

    /**
     * Verifica las credenciales de un administrador.
     * @param correo El correo a verificar.
     * @param password La contraseña a verificar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean verificarAdministradorRegistrado(String correo, String password) {
        // CORRECCIÓN: Se añade '!= null' para que el método devuelva un booleano.
        // El servicio retorna un objeto Administrativo si tiene éxito, o null si falla.
        return servicioAdministrativo.verificarAdministrativoRegistrado(correo, password) != null;
    }
}