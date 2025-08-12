package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios.SeleccionPagoServicios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaSeleccionPagoServicios {

    private Stage stage;
    private ControlSeleccionPagoServicios control;

    // Componentes del FXML
    @FXML private Label idTotal;
    @FXML private Button idPagoCaja;
    @FXML private Button idPagoLinea;
    @FXML private Button idVolver;

    public void muestra(ControlSeleccionPagoServicios control, int total) {
        this.control = control;
        try {
            // Asegúrate de que este es el nombre de tu FXML para esta ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/MenuSemanal/SeleccionPagoMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Elegir Método de Pago");
            stage.setScene(new Scene(root));
            stage.setResizable(false); 
            stage.setWidth(600);       
            stage.setHeight(420);      
            stage.show();

            idTotal.setText("$" + total);

            // Conectamos los botones a los métodos del controlador
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