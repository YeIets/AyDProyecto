package mx.uam.ayd.proyecto.presentacion.login;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class VentanaLogin {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoPassword;
    @FXML private MenuButton menuTipoUsuario;
    @FXML private Button botonIniciarSesion;

    private ControlLogin control;

    public void setControl(ControlLogin control) {
        this.control = control;
    }

    public void muestra(Stage stage) {
        stage.setTitle("Inicio de Sesión");
        stage.show();
    }

    @FXML
    private void initialize() {
        // Aquí podrías agregar eventos si quieres desde el FXML
        botonIniciarSesion.setOnAction(event -> {
            String usuario = campoUsuario.getText();
            String password = campoPassword.getText();
            String tipo = menuTipoUsuario.getText();

            control.verificaCredenciales(usuario, password, tipo);
        });
    }
}
