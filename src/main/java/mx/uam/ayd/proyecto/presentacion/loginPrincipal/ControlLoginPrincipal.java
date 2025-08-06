package mx.uam.ayd.proyecto.presentacion.loginPrincipal;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.ControlPadresPrincipal;
import mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.ControlAdministrativoPrincipal;
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.ControlEncargadoCocinaPrincipal;

@Component
public class ControlLoginPrincipal {
	
	private static final Logger log = LoggerFactory.getLogger(ControlLoginPrincipal.class);

	private final ControlPadresPrincipal controlPadresPrincipal;
	private final ControlAdministrativoPrincipal controlAdministrativoPrincipal;
	private final ControlEncargadoCocinaPrincipal controlEncargadoCocinaPrincipal;
	private final VentanaLoginPrincipal ventana;

	@Autowired
	public ControlLoginPrincipal(
		VentanaLoginPrincipal ventana, 
		ControlPadresPrincipal controlPadresPrincipal,
		ControlAdministrativoPrincipal controlAdministrativoPrincipal,
		ControlEncargadoCocinaPrincipal controlEncargadoCocinaPrincipal) 
	{
		this.controlPadresPrincipal = controlPadresPrincipal;
		this.controlAdministrativoPrincipal = controlAdministrativoPrincipal;
		this.controlEncargadoCocinaPrincipal = controlEncargadoCocinaPrincipal;
		this.ventana = ventana;
	}
	
	/**
	 * Método que se ejecuta después de la construcción del bean
	 * y realiza la conexión bidireccional entre el control y la ventana
	 */
	@PostConstruct
	public void init() {
		ventana.setControlLoginPrincipal(this);
	}

	/**
	 * Inicia el caso de uso
	 */
	public void inicia() {
		ventana.muestra();
	}

	public void padresPrincipal(){
		controlPadresPrincipal.inicia();
	}

	public void administrativoPrincipal(){
		controlAdministrativoPrincipal.inicia();
	}

	public void encargadoCocinaPrincipal(){
		controlEncargadoCocinaPrincipal.inicia();
	}

}
