package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.VerPedidos;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaVerPedidos {

    private Stage stage;
    @Setter
    private ControlVerPedidos control;

    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::muestra);
            return;
        }

        try {
            stage = new Stage();
            stage.setTitle("Ver Pedidos");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasEncargadoDeCocina/Verpedidos.fxml"));
            loader.setController(control);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void oculta() {
        if (stage != null) {
            stage.hide();
        }
    }
}