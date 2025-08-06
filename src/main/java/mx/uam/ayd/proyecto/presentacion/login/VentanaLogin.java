package mx.uam.ayd.proyecto.presentacion.login;

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
public class VentanaLogin {

	private static final Logger log = LoggerFactory.getLogger(VentanaLogin.class);

	private Stage stage;
	
	private ControlLogin control;
	private boolean initialized = false;

	@FXML
	private ComboBox<String> miComboBox;


	/**
	 * Constructor without UI initialization
	 */
	public VentanaLogin() {
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
			stage.setTitle("Ventana Login");
			
			// Load FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ControlLogin.fxml"));
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
	public void setControlLogin(ControlLogin control) {
		this.control = control;
	}
	
	/**
	 * Muestra la ventana y carga los usuarios
	 * 
	 * @param usuarios La lista de usuarios a mostrar
	 */
	public void muestra() {
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(() -> this.muestra());
			return;
		}
		
		initializeUI();

		if (miComboBox != null) {
		miComboBox.getItems().clear();
		miComboBox.getItems().addAll("Admin", "Invitado", "Usuario");
		}


		stage.show();
	}

	// FXML Handle Events
	@FXML
	private void handleIniciarSesion() {
		String campo = miComboBox.getValue();

		if (campo != null) {
			log.info("El combobox tiene valor: " + campo);
		}else{
			log.info("No se selecciono ningun valor" );
		}


	}

}
