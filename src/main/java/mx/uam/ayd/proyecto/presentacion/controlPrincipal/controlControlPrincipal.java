package mx.uam.ayd.proyecto.presentacion.controlPrincipal;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@Component
public class controlControlPrincipal {

    @FXML
    private TextField idUsuario;

    @FXML
    private PasswordField idContraseña;

    @FXML
    private MenuButton idRol;

    @FXML
    private Button idAceptar;

    private String rolSeleccionado;

    @PostConstruct
    public void initialize() {
        // Agrega listeners a los MenuItem del MenuButton
        for (MenuItem item : idRol.getItems()) {
            item.setOnAction(e -> {
                rolSeleccionado = item.getText();
                idRol.setText(rolSeleccionado);
            });
        }

        // Manejo del botón aceptar
        idAceptar.setOnAction(e -> {
            String usuario = idUsuario.getText();
            String contraseña = idContraseña.getText();
            verificaCredenciales(usuario, contraseña, rolSeleccionado);
        });
    }

    // ✅ MÉTODO PÚBLICO requerido por la vista
    public void verificaCredenciales(String usuario, String contraseña, String rol) {
        if (usuario == null || usuario.isEmpty() ||
                contraseña == null || contraseña.isEmpty() ||
                rol == null || rol.isEmpty()) {
            mostrarAlerta("Campos incompletos", "Por favor, ingresa usuario, contraseña y selecciona un rol.");
            return;
        }

        // Aquí iría la lógica real de autenticación, por ahora simulamos
        if (usuario.equals("admin") && contraseña.equals("1234")) {
            mostrarAlerta("Éxito", "Inicio de sesión correcto como " + rol);
            // Aquí puedes redirigir según el rol
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
