package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoMenuLinea;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadresPagosElegir.ControlPadresPagosElegir;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class VentanaPagoLineaMenu {

    private Stage stage;
    private ControlPagoLineaMenu control;

    @FXML
    private Label idTotal;

    @FXML
    private Label idLinea;

    @FXML
    private Label idFecha;

    @FXML
    private Button idCancelar;

    @Autowired
    private ControlPadresPagosElegir controlPadresPagosElegir;

    /**
     * Muestra la ventana de pago en línea con el total, línea de captura y fecha
     * @param control controlador asociado
     * @param total el total a mostrar
     */
    public void muestra(ControlPagoLineaMenu control, int total) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control, total));
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PagoLineaMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Pago en Línea");
            stage.setScene(new Scene(root));
            stage.show();

            // Asignar valores a los campos
            idTotal.setText("$" + total);
            idLinea.setText(generarLineaCaptura());
            idFecha.setText(obtenerFechaActual());

            idCancelar.setOnAction(e -> {
                cerrar();
                // Volver a la ventana de pagos
                controlPadresPagosElegir.regresar();
            });

        } catch (IOException e) {
            System.err.println("Error al cargar VentanaPagoLineaMenu.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generarLineaCaptura() {
        Random random = new Random();
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            linea.append(random.nextInt(10));
        }
        return linea.toString();
    }

    private String obtenerFechaActual() {
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(hoy);
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}
