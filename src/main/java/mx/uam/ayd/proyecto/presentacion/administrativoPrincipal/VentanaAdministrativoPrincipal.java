package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaAdministrativoPrincipal{

    private static final Logger log = LoggerFactory.getLogger(VentanaAdministrativoPrincipal.class);

    private Stage stage;

    private ControlAdministrativoPrincipal control;
    private boolean initialized = false;

    public VentanaAdministrativoPrincipal() {
        // Constructor
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
            stage.setTitle("Ventana Administrativo Principal");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasAdministrativo/AdministrativoPrincipal.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 600, 420);
            stage.setScene(scene);

            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setControlAdministrativoPrincipal(ControlAdministrativoPrincipal control) {
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
    private void handleDocumentacion() {
        log.info("Se presiono documentacion");
        // <-- CAMBIO AQUÍ: Llamamos al nuevo método del controlador
        control.irABuscarDocumentos();
    }
}