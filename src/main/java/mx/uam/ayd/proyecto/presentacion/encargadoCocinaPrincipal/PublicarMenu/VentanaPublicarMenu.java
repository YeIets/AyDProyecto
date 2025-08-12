package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.PublicarMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert; // Importar la clase Alert para la ventana de confirmación
import javafx.scene.control.Alert.AlertType; // Importar AlertType

@Component
public class VentanaPublicarMenu {

    private static final Logger log = LoggerFactory.getLogger(VentanaPublicarMenu.class);

    private Stage stage;
    private ControlPublicarMenu control;

    @FXML
    private Button btnPublicar;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblMensaje;

    public void muestra(ControlPublicarMenu control) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.muestra(control));
            return;
        }

        try {
            // Se corrige la ruta del FXML para que coincida con la estructura de tu proyecto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasEncargadoDeCocina/Ventanapublicarmenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();


            stage = new Stage();
            stage.setTitle("Publicar Menú Semanal");
            stage.setScene(new Scene(root));
            stage.setResizable(false); 
            stage.setWidth(600);       
            stage.setHeight(420);      
            stage.show();

            // Configurar los manejadores de eventos para los botones
            btnPublicar.setOnAction(event -> handlePublicar());
            btnRegresar.setOnAction(event -> handleRegresar());

        } catch (IOException e) {
            log.error("Error loading FXML for VentanaPublicarMenu", e);
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePublicar() {
        log.info("Se presionó el botón Publicar");
        // Lógica para publicar el menú

        // Llamar al método para mostrar la ventana de confirmación
        mostrarMensajeConfirmacion("¡Éxito!", "El menú ha sido publicado correctamente.");
    }

    private void mostrarMensajeConfirmacion(String titulo, String mensaje) {
        // Se crea una ventana de alerta de tipo INFORMATION
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // No queremos un header text
        alert.setContentText(mensaje);

        // Mostrar la ventana y esperar a que el usuario la cierre
        alert.showAndWait();
    }

    @FXML
    private void handleRegresar() {
        log.info("Se presionó el botón Regresar");
        control.regresar();
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}
