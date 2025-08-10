package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioEncargadoCocina;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ControlEncargadoCocinaPrincipal {
	
	private static final Logger log = LoggerFactory.getLogger(ControlEncargadoCocinaPrincipal.class);
	
	private final VentanaEncargadoCocinaPrincipal ventana;
 
    @Autowired
    private ServicioEncargadoCocina servicioEncargadoCocina;

	@Autowired
	public ControlEncargadoCocinaPrincipal(VentanaEncargadoCocinaPrincipal ventana) {
		this.ventana = ventana;
	}
	
	/**
	 * Método que se ejecuta después de la construcción del bean
	 * y realiza la conexión bidireccional entre el control y la ventana
	 */
	@PostConstruct
	public void init() {
		ventana.setControlEncargadoCocinaPrincipal(this);
	}

	/**
	 * Inicia el caso de uso
	 */
	public void inicia() {
		ventana.muestra();
	}

    //Añade un Encargado a la base de datos
    public void agregarEncargadoCocina(String nombre, String contraseña){
        servicioEncargadoCocina.agregarEncargadoCocina(nombre, contraseña);
    }

}
