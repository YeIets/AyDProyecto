package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.PublicarMenu;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@Component
public class VentanaPublicarMenu {

    private static final Logger log = LoggerFactory.getLogger(VentanaPublicarMenu.class);

    private Stage stage;
    private ControlPublicarMenu control;

    @FXML private TextField lunesPlatillo;
    @FXML private TextField lunesBebida;
    @FXML private TextField lunesFruta;
    @FXML private TextField lunesPostre;

    @FXML private TextField martesPlatillo;
    @FXML private TextField martesBebida;
    @FXML private TextField martesFruta;
    @FXML private TextField martesPostre;

    @FXML private TextField miercolesPlatillo;
    @FXML private TextField miercolesBebida;
    @FXML private TextField miercolesFruta;
    @FXML private TextField miercolesPostre;

    @FXML private TextField juevesPlatillo;
    @FXML private TextField juevesBebida;
    @FXML private TextField juevesFruta;
    @FXML private TextField juevesPostre;

    @FXML private TextField viernesPlatillo;
    @FXML private TextField viernesBebida;
    @FXML private TextField viernesFruta;
    @FXML private TextField viernesPostre;

    @FXML private Button btnPublicar;
    @FXML private Button btnRegresar;

    public void muestra(ControlPublicarMenu control) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasEncargadoDeCocina/Ventanapublicarmenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Publicar Menú Semanal");
            stage.setScene(new Scene(root));
            stage.show();

            // ✅ CORRECCIÓN DEFINITIVA: Se añaden estas líneas para forzar la activación de los botones,
            // sin importar si el 'onAction' está en el FXML. Esto solucionará el problema.
            btnPublicar.setOnAction(event -> handlePublicar());
            btnRegresar.setOnAction(event -> handleRegresar());

        } catch (IOException e) {
            log.error("Error al cargar FXML", e);
        }
    }

    @FXML
    private void handlePublicar() {
        log.info("Se presionó el botón Publicar");

        control.publicarMenu("Lunes", lunesPlatillo.getText(), lunesBebida.getText(), lunesFruta.getText(), lunesPostre.getText());
        control.publicarMenu("Martes", martesPlatillo.getText(), martesBebida.getText(), martesFruta.getText(), martesPostre.getText());
        control.publicarMenu("Miércoles", miercolesPlatillo.getText(), miercolesBebida.getText(), miercolesFruta.getText(), miercolesPostre.getText());
        control.publicarMenu("Jueves", juevesPlatillo.getText(), juevesBebida.getText(), juevesFruta.getText(), juevesPostre.getText());
        control.publicarMenu("Viernes", viernesPlatillo.getText(), viernesBebida.getText(), viernesFruta.getText(), viernesPostre.getText());

        mostrarMensajeConfirmacion("¡Éxito!", "El menú ha sido actualizado correctamente.");
    }

    @FXML
    private void handleRegresar() {
        control.regresar();
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }

    private void mostrarMensajeConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}