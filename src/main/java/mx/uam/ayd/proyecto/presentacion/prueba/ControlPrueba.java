package mx.uam.ayd.proyecto.presentacion.prueba;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ControlPrueba {
	
	private static final Logger log = LoggerFactory.getLogger(ControlPrueba.class);
	
	private final VentanaPrueba ventana;

	@Autowired
	public ControlPrueba(VentanaPrueba ventana) {
		this.ventana = ventana;
	}
	
	/**
	 * Método que se ejecuta después de la construcción del bean
	 * y realiza la conexión bidireccional entre el control y la ventana
	 */
	@PostConstruct
	public void init() {
		ventana.setControlPrueba(this);
	}

	/**
	 * Inicia el caso de uso
	 */
	public void inicia() {
		ventana.muestra();
	}

}
