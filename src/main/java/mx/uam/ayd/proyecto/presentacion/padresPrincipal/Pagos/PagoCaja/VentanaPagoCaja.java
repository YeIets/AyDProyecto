package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu.ControlSeleccionPagoMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class VentanaPagoCaja {

    private Stage stage;
    private ControlPagoCaja control;

    @FXML
    private Label idTotal;

    @FXML
    private Label idLinea; // Corresponde al "Numero de referencia"

    @FXML
    private Label idFecha;

    @FXML
    private Button idCancelar;

    @Autowired
    private ControlSeleccionPagoMenu controlSeleccionPagoMenu;

    /**
     * Muestra la ventana de pago en caja con el total, número de referencia y fecha.
     * @param control el controlador asociado a esta ventana
     * @param total el total a pagar
     */
    public void muestra(ControlPagoCaja control, int total) {
        this.control = control;

        // Asegura que la operación se ejecute en el hilo de la UI de JavaFX
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control, total));
            return;
        }

        try {
            // Carga el archivo FXML para la ventana de pago en caja
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/Pagos/PagoCajaMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Pago en Caja 6");
            stage.setScene(new Scene(root));
            stage.setResizable(false); 
            stage.setWidth(600);       
            stage.setHeight(420);      
            stage.show();

            // Asigna los valores a las etiquetas de la interfaz
            idTotal.setText("$" + total);
            idLinea.setText(generarNumeroReferencia());
            idFecha.setText(obtenerFechaActual());

            // Define la acción del botón "Cancelar"
            idCancelar.setOnAction(e -> {
                cerrar();
                // Regresa a la ventana anterior (donde se elige el método de pago)
                controlSeleccionPagoMenu.regresar();
            });

        } catch (IOException e) {
            System.err.println("Error al cargar VentanaPagoCajaMenu.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Genera un número de referencia aleatorio de 10 dígitos.
     * @return una cadena con el número de referencia
     */
    private String generarNumeroReferencia() {
        Random random = new Random();
        StringBuilder referencia = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            referencia.append(random.nextInt(10));
        }
        return referencia.toString();
    }

    /**
     * Obtiene la fecha actual formateada como "dd/MM/yyyy".
     * @return una cadena con la fecha actual
     */
    private String obtenerFechaActual() {
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(hoy);
    }

    /**
     * Cierra la ventana actual.
     */
    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}