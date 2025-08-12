package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // CAMBIO: Se importa la entidad Alumno
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class VentanaPagoEnLinea {

    private Stage stage;
    private ControlPagoEnLinea control;

    @FXML private Label idTotal;
    @FXML private Label idLinea;
    @FXML private Label idFecha;
    @FXML private Button idCancelar;

    /**
     * CAMBIO: Muestra el recibo de pago para un alumno específico.
     * @param control El controlador de esta ventana.
     * @param total El monto total pagado.
     * @param alumno El alumno para el cual se generó el pago.
     */
    public void muestra(ControlPagoEnLinea control, int total, Alumno alumno) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/Pagos/PagoLineaMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();

            // CAMBIO: Se personaliza el título de la ventana con el nombre del alumno.
            stage.setTitle("Recibo de Pago para " + alumno.getNombreCompleto());

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);

            idTotal.setText("$" + total);
            idLinea.setText(generarLineaCaptura());
            idFecha.setText(obtenerFechaActual());

            idCancelar.setOnAction(e -> cerrar());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
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
}