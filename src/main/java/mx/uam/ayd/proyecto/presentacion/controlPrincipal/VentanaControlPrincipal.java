package mx.uam.ayd.proyecto.presentacion.controlPrincipal;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

@Component
public class VentanaControlPrincipal {

    private Stage stage;
    private boolean initialized = false;

    @FXML private TextField idUsuario;
    @FXML private PasswordField idContraseña;
    @FXML private Button idAceptar;
    @FXML private MenuButton idRol;
    @FXML private MenuItem idMenuPadre;
    @FXML private MenuItem idMenuAdmin;
    @FXML private MenuItem idMenuCocina;
    @FXML private ImageView logoInstitucional;

    private controlControlPrincipal control;

    public VentanaControlPrincipal() {
        // Constructor vacío, sin inicializar interfaz aquí
    }

    private void initializeUI() {
        if (initialized)
            return;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }

        try {
            stage = new Stage();
            stage.setTitle("Inicio de sesión");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-control-principal.fxml"));
            loader.setController(this);

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            // Aquí podrías agregar listeners si lo deseas
            idAceptar.setOnAction(e -> control.verificaCredenciales(idUsuario.getText(), idContraseña.getText(), idRol.getText()));

            initialized = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setControl(controlControlPrincipal control) {
        this.control = control;
    }

    public void muestra() {
        if (!initialized) {
            initializeUI();
        }

        if (!stage.isShowing()) {
            stage.show();
        }
    }

    public void cierra() {
        if (stage != null && stage.isShowing()) {
            stage.close();
        }
    }
}
