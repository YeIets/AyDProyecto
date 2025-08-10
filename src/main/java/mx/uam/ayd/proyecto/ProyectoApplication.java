package mx.uam.ayd.proyecto;

import mx.uam.ayd.proyecto.datos.AdministrativoRepository;
import mx.uam.ayd.proyecto.datos.EncargadoCocinaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Administrativo;
import mx.uam.ayd.proyecto.negocio.modelo.EncargadoCocina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import mx.uam.ayd.proyecto.presentacion.loginPrincipal.ControlLoginPrincipal;
import mx.uam.ayd.proyecto.datos.PadreRepository;

/**
 * 
 * Clase principal que arranca la aplicación 
 * construida usando el principio de 
 * inversión de control
 * Adaptada para usar JavaFX
 * 
 * @author Humberto Cervantes (c) 21 Nov 2022
 */
@SpringBootApplication
public class ProyectoApplication {

	private final ControlLoginPrincipal controlLoginPrincipal;
	private final PadreRepository padreRepository;
	private final AdministrativoRepository administrativoRepository;
	private final EncargadoCocinaRepository encargadoCocinaRepository;
	
	@Autowired
	public ProyectoApplication(ControlLoginPrincipal controlLoginPrincipal, PadreRepository padreRepository, AdministrativoRepository administrativoRepository, EncargadoCocinaRepository encargadoCocinaRepository) {
		this.controlLoginPrincipal = controlLoginPrincipal;
		this.padreRepository = padreRepository;
        this.administrativoRepository = administrativoRepository;
        this.encargadoCocinaRepository = encargadoCocinaRepository;
    }

	/**
	 * Método principal
	 *
	 * @param args argumentos de la línea de comando
	 */
	public static void main(String[] args) {
		// Launch JavaFX application
		Application.launch(JavaFXApplication.class, args);
	}
	
	/**
	 * Clase interna para manejar la inicialización de JavaFX
	 */
	public static class JavaFXApplication extends Application {
		
		private static ConfigurableApplicationContext applicationContext;
		
		@Override
		public void init() throws Exception {
			// Create Spring application context
			SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);
			builder.headless(false);
			applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
		}
		
		@Override
		public void start(Stage primaryStage) {
			// Initialize the application on the JavaFX thread
			Platform.runLater(() -> {
				applicationContext.getBean(ProyectoApplication.class).inicia();
			});
		}
		
		@Override
		public void stop() throws Exception {
			applicationContext.close();
			Platform.exit();
		}
	}
	
	/**
	 * Metodo que arranca la aplicacion
	 * inicializa la bd y arranca el controlador
	 */
	public void inicia() {
		inicializaBD();
		
		// Make sure controllers are created on JavaFX thread
		Platform.runLater(() -> {
			controlLoginPrincipal.inicia();
		});
	}
	
	/**
	 * Inicializa la BD con datos
	 */
	public void inicializaBD() {
		Padre padre = new Padre("Juan@uam.com", "Juan123");
		Administrativo admin = new Administrativo("Humberto@uam.com", "Humberto123");
		EncargadoCocina encargado = new EncargadoCocina("Alberto@uam.com", "Alberto123");
		padreRepository.save(padre);
		administrativoRepository.save(admin);
		encargadoCocinaRepository.save(encargado);
	}
}
