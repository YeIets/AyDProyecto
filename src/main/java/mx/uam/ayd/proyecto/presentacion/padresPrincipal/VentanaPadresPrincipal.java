package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Ventana para listar usuarios usando JavaFX con FXML
 */
@Component
public class VentanaPadresPrincipal {

	private static final Logger log = LoggerFactory.getLogger(VentanaPadresPrincipal.class);

	private Stage stage;
	
	private ControlPadresPrincipal control;
	private boolean initialized = false;

	/**
	 * Constructor without UI initialization
	 */
	public VentanaPadresPrincipal() {
		// Don't initialize JavaFX components in constructor
	}
	
	/**
	 * Initialize UI components on the JavaFX application thread
	 */
	private void initializeUI() {
		if (initialized) {
			return;
		}
		
		// Create UI only if we're on JavaFX thread
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(this::initializeUI);
			return;
		}
		
		try {
			stage = new Stage();
			stage.setTitle("Ventana Padres Principal");
			
			// Load FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PadresPrincipal.fxml"));
			loader.setController(this);
			Scene scene = new Scene(loader.load(), 450, 400);
			stage.setScene(scene);
			
			initialized = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Establece el controlador asociado a esta ventana
	 * 
	 * @param control El controlador asociado
	 */
	public void setControlPadresPrincipal(ControlPadresPrincipal control) {
		this.control = control;
	}
	

	//Muestra la ventana
	public void muestra() {
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(() -> this.muestra());
			return;
		}
		
		initializeUI();
		stage.show();
	}

	// FXML Handle Events

	//Declara las funciones del boton SubirDocumentos
	@FXML
	private void handleSubirDocumentos() {
		log.info("Se presiono subir documento");
	}

	//Declara las funciones del boton HacerPagos
	@FXML
	private void handleHacerPagos() {
		log.info("Se presiono hacer pagos");
	}
	//Declara las funciones del boton MenuSemanal
	@FXML
	private void handleMenuSemanal() {
		log.info("Se presiono menu semanal");
	}

}
