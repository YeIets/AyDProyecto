package mx.uam.ayd.proyecto.presentacion.login;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ControlLogin {
	
	private static final Logger log = LoggerFactory.getLogger(ControlLogin.class);
	
	private final VentanaLogin ventana;

	@Autowired
	public ControlLogin(VentanaLogin ventana) {
		this.ventana = ventana;
	}
	
	/**
	 * Método que se ejecuta después de la construcción del bean
	 * y realiza la conexión bidireccional entre el control y la ventana
	 */
	@PostConstruct
	public void init() {
		ventana.setControlLogin(this);
	}

	/**
	 * Inicia el caso de uso
	 */
	public void inicia() {
		ventana.muestra();
	}

}
