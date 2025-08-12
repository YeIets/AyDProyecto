package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // Se importa la entidad Alumno
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class VentanaSeleccionPagoMenu {

    private Stage stage;
    private ControlSeleccionPagoMenu control;

    // Componentes del FXML
    @FXML private Label idTotal;
    @FXML private Button idPagoCaja;
    @FXML private Button idPagoLinea;
    @FXML private Button idVolver;

    /**
     * CAMBIO: El método ahora acepta un Alumno como tercer argumento.
     * @param control El controlador de esta ventana.
     * @param total El monto total a pagar.
     * @param alumno El alumno para el cual se realiza la operación.
     */
    public void muestra(ControlSeleccionPagoMenu control, int total, Alumno alumno) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/MenuSemanal/SeleccionPagoMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Elegir Método de Pago");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);

            idTotal.setText("$" + total);

            configurarBotones();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configurarBotones() {
        idPagoCaja.setOnAction(e -> control.irAPagoCaja());
        idPagoLinea.setOnAction(e -> control.irAPagoLinea());
        idVolver.setOnAction(e -> control.regresar());
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}