package mx.uam.ayd.proyecto.presentacion.padresPrincipal.Pagos.PagoCaja;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno; // CAMBIO: Se importa la entidad Alumno
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
     * CAMBIO: Muestra la ventana para un alumno específico.
     * @param control el controlador asociado a esta ventana.
     * @param total el total a pagar.
     * @param alumno el alumno para el cual se realiza la operación.
     */
    public void muestra(ControlPagoCaja control, int total, Alumno alumno) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            // CAMBIO: Se pasa el alumno en la llamada recursiva.
            Platform.runLater(() -> muestra(control, total, alumno));
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/Pagos/PagoCajaMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            // CAMBIO: Se personaliza el título de la ventana.
            stage.setTitle("Pago en Caja para " + alumno.getNombreCompleto());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);
            stage.show();

            idTotal.setText("$" + total);
            idLinea.setText(generarNumeroReferencia());
            idFecha.setText(obtenerFechaActual());

            idCancelar.setOnAction(e -> {
                cerrar();
                // Esta llamada puede necesitar ser ajustada si ControlSeleccionPagoMenu también necesita el alumno.
                controlSeleccionPagoMenu.regresar();
            });

        } catch (IOException e) {
            System.err.println("Error al cargar VentanaPagoCajaMenu.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generarNumeroReferencia() {
        Random random = new Random();
        StringBuilder referencia = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            referencia.append(random.nextInt(10));
        }
        return referencia.toString();
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