package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoEnLinea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // <-- Se usan solo Labels
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class VentanaPagoEnLinea {

    private Stage stage;
    private ControlPagoEnLinea control;

    // CORREGIDO: Los campos @FXML ahora coinciden con tu FXML (solo Labels y un Button)
    @FXML private Label idTotal;
    @FXML private Label idLinea;
    @FXML private Label idFecha;
    @FXML private Button idCancelar; // El botón "Volver" de tu FXML

    public void muestra(ControlPagoEnLinea control, int total) {
        this.control = control;
        try {
            // Carga el FXML del ticket, que es el que estaba dando el error
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/Pagos/PagoLineaMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Recibo de Pago");

            // CORREGIDO: La lógica ahora llena los Labels del ticket
            idTotal.setText("$" + total);
            idLinea.setText(generarLineaCaptura());
            idFecha.setText(obtenerFechaActual());

            // Conecta el botón "Volver" del FXML (idCancelar) a la acción de cerrar
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

    // --- MÉTODOS DE AYUDA PARA GENERAR LOS DATOS DEL TICKET ---

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

    // ELIMINADO: Se quitaron los getters (getNumeroTarjeta, etc.) y el diálogo de éxito,
    // ya que no pertenecen a esta ventana de "ticket".
}