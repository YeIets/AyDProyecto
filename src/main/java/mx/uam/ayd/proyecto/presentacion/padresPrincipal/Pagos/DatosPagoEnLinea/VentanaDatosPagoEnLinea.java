package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.DatosPagoEnLinea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class VentanaDatosPagoEnLinea {

    private Stage stage;
    private ControlDatosPagoEnLinea control;

    // Componentes del FXML
    @FXML private TextField idNoTarjeta;
    @FXML private TextField idFecha;
    @FXML private PasswordField idCVV;
    @FXML private Label idTotal;
    @FXML private Button idPagar;
    @FXML private Button idRegresar;

    public void muestra(ControlDatosPagoEnLinea control, int total) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/Pagos/PagoLineaTarjeta.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();
            stage.setTitle("Datos de Pago");

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);

            idTotal.setText("$" + total);

            // Se cambia la acción del botón para que primero valide
            idPagar.setOnAction(e -> control.validarYProcesarPago());
            idRegresar.setOnAction(e -> control.regresar());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para que el controlador pueda obtener los datos de la vista
    public String getNumeroTarjeta() {
        return idNoTarjeta.getText();
    }

    public String getFechaVencimiento() {
        return idFecha.getText();
    }

    public String getCVV() {
        return idCVV.getText();
    }

    public void muestraDialogoDeExito() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pago Exitoso");
        alert.setHeaderText(null);
        alert.setContentText("¡El pago se ha procesado correctamente!");
        alert.showAndWait();
    }

    // MÉTODO AÑADIDO para mostrar los errores de validación
    public void muestraDialogoDeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Validación");
        alert.setHeaderText("Por favor, corrige los siguientes errores:");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}