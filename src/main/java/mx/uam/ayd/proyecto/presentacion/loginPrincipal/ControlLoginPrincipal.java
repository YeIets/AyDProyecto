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

	//Se declaran los controles que interactuan con el ControlLoginPrincipal
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

	
	//Metodo que inicia / muestra la ventana
	public void inicia() {
		ventana.muestra();
	}

	//Metodo que muestra la pantalla principal para padres
	public void padresPrincipal(){
		ventana.cerrar();
		controlPadresPrincipal.inicia();
	}

	//Metodo que muestra la pantalla principal para administrativos
	public void administrativoPrincipal(){
		ventana.cerrar();
		controlAdministrativoPrincipal.inicia();
	}

	//Metodo que muestra la pantalla principal para cocineros
	public void encargadoCocinaPrincipal(){
		ventana.cerrar();
		controlEncargadoCocinaPrincipal.inicia();
	}

}
