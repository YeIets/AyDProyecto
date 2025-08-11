package mx.uam.ayd.proyecto.presentacion.padresPrincipal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private Scene scene;

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
            stage.setTitle("Ventana Padres Principal 2");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/PadresPrincipal.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.scene = scene;

            stage.setScene(scene);
            stage.setWidth(600);
            stage.setHeight(420);
            stage.setResizable(false);

            stage.show();

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

        boolean hayNotificacion = control.padreTieneNotificaciones();
        actualizarIconoNotificacion(hayNotificacion);

        stage.show();
    }

    public void actualizarIconoNotificacion(boolean tieneNotificaciones) {
        Platform.runLater(() -> {
            if (iconoNotificacion != null && iconoNotificacionActiva != null) {
                iconoNotificacionActiva.setVisible(tieneNotificaciones);
                iconoNotificacion.setVisible(!tieneNotificaciones);
                log.info("Tiene notificaciones es = " + tieneNotificaciones);
            }
        });
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