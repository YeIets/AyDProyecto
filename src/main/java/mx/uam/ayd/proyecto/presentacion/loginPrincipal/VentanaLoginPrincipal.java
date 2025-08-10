package mx.uam.ayd.proyecto.presentacion.loginPrincipal;

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
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Ventana para listar usuarios usando JavaFX con FXML
 */
@Component
public class VentanaLoginPrincipal {

	private static final Logger log = LoggerFactory.getLogger(VentanaLoginPrincipal.class);

	private Stage stage;
	
	private ControlLoginPrincipal control;
	private boolean initialized = false;


    @FXML
    private TextField idUsuario;
    @FXML
    private PasswordField idPassword;

	@FXML
	private ComboBox<String> miComboBox;


	/**
	 * Constructor without UI initialization
	 */
	public VentanaLoginPrincipal() {
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
			stage.setTitle("Ventana Login Principal");
			
			// Load FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPrincipal.fxml"));
			loader.setController(this);
			Scene scene = new Scene(loader.load(), 600, 420);
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
	public void setControlLoginPrincipal(ControlLoginPrincipal control) {
		this.control = control;
	}
	

	//Muestra la ventana y se agregan opciones al combobox
	public void muestra() {
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(() -> this.muestra());
			return;
		}
		
		initializeUI();

		if (miComboBox != null) {
			miComboBox.getItems().clear();
			miComboBox.getItems().addAll("Padres", "Administradores", "Cocineros");
		}


		stage.show();
	}

	public void cerrar() {
		if (stage != null) {
			stage.close();
		}
	}

	// FXML Handle Events

	//Declara las funciones del boton IniciarSesion para cada valor del combobox
	@FXML
	private void handleIniciarSesion() {		
		
		String opcion = miComboBox.getValue();

		if (opcion != null) {
			
			switch(opcion.toLowerCase()){

			case  "padres":
				log.info("El combobox dice: " + opcion);
				control.padresPrincipal(idUsuario.getText(), idPassword.getText());
				log.info("Usuario = " + idUsuario.getText() + ", Contraseña = " + idPassword.getText());
				break;

			case  "administradores":
				log.info("El combobox dice: " + opcion);
				control.administrativoPrincipal(idUsuario.getText(), idPassword.getText());
				log.info("Usuario = " + idUsuario.getText() + ", Contraseña = " + idPassword.getText());
				break;

			case  "cocineros":
				log.info("El combobox dice: " + opcion);
				control.encargadoCocinaPrincipal(idUsuario.getText(), idPassword.getText());
				log.info("Usuario = " + idUsuario.getText() + ", Contraseña = " + idPassword.getText());
				break;

			default:
				break;

			}
		}else {
			log.info("No hay opcion seleccionada en ComboBox");
			mostrarMensajeError("Tienes que seleccionar un Rol");
		}
	}

	public void mostrarMensajeError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
    	alerta.setHeaderText("Error al iniciar sesion"); // puedes poner un encabezado si quieres
    	alerta.setContentText(mensaje);
    	alerta.showAndWait();
    }


}
