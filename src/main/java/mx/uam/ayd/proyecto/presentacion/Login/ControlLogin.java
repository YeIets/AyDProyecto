package mx.uam.ayd.proyecto.presentacion.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlLogin {

    public void inicia(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            VentanaLogin ventana = loader.getController();
            ventana.setControl(this);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            ventana.muestra(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verificaCredenciales(String usuario, String password, String tipoUsuario) {
        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);
        System.out.println("Tipo de usuario: " + tipoUsuario);

        // Aquí iría la lógica para validar con los servicios
    }
}
