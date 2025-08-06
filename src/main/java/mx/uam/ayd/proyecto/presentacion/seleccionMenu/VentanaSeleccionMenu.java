package mx.uam.ayd.proyecto.presentacion.seleccionMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaSeleccionMenu {

    private Stage stage;
    private ControlSeleccionMenu control;

    @FXML private CheckBox idLunesCheck;
    @FXML private CheckBox idMartesCheck;
    @FXML private CheckBox idMiercolesCheck;
    @FXML private CheckBox idJuvesCheck;
    @FXML private CheckBox idViernesCheck;
    @FXML private Label idTotal;
    @FXML private Button idPago;
    @FXML private Button idVolver;

    private boolean initialized = false;

    public void muestra(ControlSeleccionMenu control) {
        this.control = control;

        if (!initialized) {
            initializeUI();
        }

        if (!stage.isShowing()) {
            stage.show();
        }
    }

    private void initializeUI() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::initializeUI);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-seleccion-menu.fxml"));
            loader.setController(this);

            stage = new Stage();
            stage.setTitle("Seleccionar Días del Menú Semanal");
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);

            initialized = true;

            setupEventHandlers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupEventHandlers() {
        // Cada vez que se cambia un CheckBox, se recalcula el total
        idLunesCheck.setOnAction(e -> actualizarTotal());
        idMartesCheck.setOnAction(e -> actualizarTotal());
        idMiercolesCheck.setOnAction(e -> actualizarTotal());
        idJuvesCheck.setOnAction(e -> actualizarTotal());
        idViernesCheck.setOnAction(e -> actualizarTotal());

        idPago.setOnAction(e -> control.pagar());
        idVolver.setOnAction(e -> control.volverAlMenu());
    }

    private void actualizarTotal() {
        control.actualizarTotal(
                idLunesCheck.isSelected(),
                idMartesCheck.isSelected(),
                idMiercolesCheck.isSelected(),
                idJuvesCheck.isSelected(),
                idViernesCheck.isSelected()
        );
    }

    public void actualizaTotal(String totalTexto) {
        idTotal.setText(totalTexto);
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}
