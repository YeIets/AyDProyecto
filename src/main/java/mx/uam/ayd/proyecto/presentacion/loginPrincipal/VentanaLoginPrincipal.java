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

	//Establece el controlador asociado a la ventana
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
    	String usuario = idUsuario.getText();
    	String password = idPassword.getText();

    	if (verificarCamposNoVacios()) {
    		mostrarMensajeError("Los campos estan vacios");
    		return;
    	}

    	if (opcion == null) {
    		log.info("No hay opción seleccionada en ComboBox");
    		mostrarMensajeError("Tienes que seleccionar un Rol");
    		return;
    	}

		switch (opcion.toLowerCase()) {
		    case "padres" -> {
		    	if (control.verificarPadreRegistrado(usuario,password)) {
		        	control.padresPrincipal(usuario, password);
		    	}else mostrarMensajeError("El usuario no esta registrado");
		    }
		    case "administradores" -> {
				if (control.verificarAdministradorRegistrado(usuario,password)) {
		        	control.administrativoPrincipal(usuario, password);
		    	}else mostrarMensajeError("El usuario no esta registrado");
		    }
		    case "cocineros" -> {
				if (control.verificarEncargadoDeCocinaRegistrado(usuario,password)) {
		        	control.encargadoCocinaPrincipal(usuario, password);
		    	}else mostrarMensajeError("El usuario no esta registrado");
		    }
		    default -> mostrarMensajeError("Rol no existente");
		}


    }

    private boolean verificarCamposNoVacios(){
    	return isEmpty(idUsuario) && isEmpty(idPassword);
    }

	//Revisa si un campo de texto es vacio, regresa True si es vacio, False si no
    private boolean isEmpty(TextField textField) {
    	String texto = textField.getText();
    	return texto == null || texto.trim().isEmpty();
    }

	//Muestra un mensaje de error
    public void mostrarMensajeError(String mensaje) {
    	Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error"); //Titulo de la ventana
    	alerta.setHeaderText("Error al iniciar sesion"); // El encabezado del error
    	alerta.setContentText(mensaje); //Texto del error
    	alerta.showAndWait();
    }

}
