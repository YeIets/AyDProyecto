package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioAdministrativo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ControlAdministrativoPrincipal {
	
	private static final Logger log = LoggerFactory.getLogger(ControlAdministrativoPrincipal.class);
	
	private final VentanaAdministrativoPrincipal ventana;

    @Autowired
    private ServicioAdministrativo servicioAdministrativo;

	@Autowired
	public ControlAdministrativoPrincipal(VentanaAdministrativoPrincipal ventana) {
		this.ventana = ventana;
	}
	
	/**
	 * Método que se ejecuta después de la construcción del bean
	 * y realiza la conexión bidireccional entre el control y la ventana
	 */
	@PostConstruct
	public void init() {
		ventana.setControlAdministrativoPrincipal(this);
	}

	/**
	 * Inicia el caso de uso
	 */
	public void inicia() {
		ventana.muestra();
	}

    public void agregarAdministrativo(String nombre, String contraseña){
        servicioAdministrativo.agregarAdministrativo(nombre, contraseña);
    }
}
