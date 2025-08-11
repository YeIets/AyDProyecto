package mx.uam.ayd.proyecto.presentacion.loginPrincipal;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Ventana para el inicio de sesión principal.
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

    public VentanaLoginPrincipal() {
        // No se inicializan componentes de JavaFX en el constructor
    }

    private void initializeUI() {
        if (initialized) {
            return;
        }

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }

        try {
            stage = new Stage();
            stage.setTitle("Ventana Login Principal");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPrincipal.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 600, 420);
            stage.setScene(scene);

            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setControlLoginPrincipal(ControlLoginPrincipal control) {
        this.control = control;
    }

    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::muestra);
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

    @FXML
    private void handleIniciarSesion() {
        String opcion = miComboBox.getValue();
        String usuario = idUsuario.getText();
        String password = idPassword.getText();

        if (verificarCamposNoVacios()) {
            mostrarMensajeError("Los campos de usuario y contraseña no pueden estar vacíos.");
            return;
        }

        if (opcion == null) {
            log.info("No hay opción seleccionada en ComboBox");
            mostrarMensajeError("Debe seleccionar un Rol.");
            return;
        }

        switch (opcion.toLowerCase()) {
            case "padres" -> {
                if (control.verificarPadreRegistrado(usuario, password)) {
                    // CORRECCIÓN: Se llama al método sin argumentos.
                    control.padresPrincipal();
                } else {
                    mostrarMensajeError("El usuario o la contraseña son incorrectos.");
                }
            }
            case "administradores" -> {
                if (control.verificarAdministradorRegistrado(usuario, password)) {
                    // CORRECCIÓN: Se llama al método sin argumentos.
                    control.administrativoPrincipal();
                } else {
                    mostrarMensajeError("El usuario o la contraseña son incorrectos.");
                }
            }
            case "cocineros" -> {
                if (control.verificarEncargadoDeCocinaRegistrado(usuario, password)) {
                    // CORRECCIÓN: Se llama al método sin argumentos.
                    control.encargadoCocinaPrincipal();
                } else {
                    mostrarMensajeError("El usuario o la contraseña son incorrectos.");
                }
            }
            default -> mostrarMensajeError("Rol no existente.");
        }
    }

    /**
     * Verifica que los campos de texto no estén vacíos.
     * @return true si alguno de los campos está vacío.
     */
    private boolean verificarCamposNoVacios() {
        // CORRECCIÓN LÓGICA: Se cambió '&&' por '||'.
        // La validación debe fallar si CUALQUIERA de los campos está vacío.
        return isEmpty(idUsuario) || isEmpty(idPassword);
    }

    private boolean isEmpty(TextField textField) {
        String texto = textField.getText();
        return texto == null || texto.trim().isEmpty();
    }

    public void mostrarMensajeError(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Error al iniciar sesión");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}