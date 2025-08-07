package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadresPagosElegir;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PedidosSemanales.VentanaSeleccionMenu;

import java.io.IOException;

@Component
public class VentanaPadresPagosElegir {

    private Stage stage;
    private ControlPadresPagosElegir control;

    @Autowired
    private VentanaSeleccionMenu ventanaSeleccionMenu;

    @FXML
    private Label idTotal;

    @FXML
    private Button idPagoCaja;

    @FXML
    private Button idPagoLinea;

    @FXML
    private Button idVolver;

    public void muestra(ControlPadresPagosElegir control, int total) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control, total));
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PadresPagosElegir.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Pago del Menú");
            stage.setScene(new Scene(root));
            stage.show();

            idTotal.setText("$" + total);

            configurarEventos();

        } catch (IOException e) {
            System.err.println("Error cargando PadresPagosElegir.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void configurarEventos() {
        // ACCIÓN DEL BOTÓN MODIFICADA
        idPagoCaja.setOnAction(e -> {
            stage.close(); // Cierra esta ventana
            control.irAPagoCaja(); // Llama al nuevo método del controlador
        });

        idPagoLinea.setOnAction(e -> {
            stage.close();
            control.irAPagoLinea();
        });

        idVolver.setOnAction(e -> {
            stage.close();
            ventanaSeleccionMenu.muestra(control.getControlSeleccionMenu());
        });
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}