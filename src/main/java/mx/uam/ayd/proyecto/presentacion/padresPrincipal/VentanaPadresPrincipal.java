package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaPadresPrincipal {

    private static final Logger log = LoggerFactory.getLogger(VentanaPadresPrincipal.class);

    private Stage stage;

    private ControlPadresPrincipal control;
    private boolean initialized = false;

    @FXML
    private ImageView iconoNotificacion;

    @FXML
    private ImageView iconoNotificacionActiva;

    public VentanaPadresPrincipal() {
        // Constructor vacío
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
            stage.setTitle("Ventana Padres Principal");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/PadresPrincipal.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 600, 420);
            stage.setScene(scene);

            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setControlPadresPrincipal(ControlPadresPrincipal control) {
        this.control = control;
    }

    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.muestra());
            return;
        }
        
        initializeUI();

        stage.show();
    }

    // FXML Handle Events
    @FXML
    private void handleCerrar() {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void handleSubirDocumentos() {
        log.info("Se presionó subir documento");
        control.irAActualizarDocumentos();
    }

    @FXML
    private void handleHacerPagos() {
        log.info("Se presionó hacer pagos");
        control.irAHacerPagos();
    }

    @FXML
    private void handleMenuSemanal() {
        log.info("Se presionó menú semanal");
        control.irAMenuSemanal();
    }
}