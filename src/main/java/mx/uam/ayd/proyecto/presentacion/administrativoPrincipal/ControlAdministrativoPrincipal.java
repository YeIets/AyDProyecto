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

    @Autowired
    private VentanaAdministrativoPrincipal ventana;

    @Autowired
    private ServicioAdministrativo servicioAdministrativo;

    @Autowired
    private ControlBuscarDocumentos controlBuscarDocumentos;

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
        // CAMBIO: Se pasa 'null' como argumento, ya que desde este punto
        // no se necesita una acción de refresco.
        controlBuscarDocumentos.inicia();
    }

    /**
     * Verifica las credenciales de un administrador.
     * @param correo El correo a verificar.
     * @param password La contraseña a verificar.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean verificarAdministradorRegistrado(String correo, String password) {
        return servicioAdministrativo.verificarAdministradorRegistrado(correo, password) != null;
    }
}