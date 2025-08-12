package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionPagoMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

/**
 * @author      Nombre del autor(es)
 * @since       1.1
 *
 * Módulo de presentación que muestra las opciones de pago (caja o en línea).
 * Ahora muestra dinámicamente el total y los conceptos a pagar.
 */
@Component
public class VentanaSeleccionPagoMenu {

    private Stage stage;
    private ControlSeleccionPagoMenu control;

    @FXML private Label idTotal;
    @FXML private Button idPagoCaja;
    @FXML private Button idPagoLinea;
    @FXML private Button idVolver;

    /**
     * ¡ACCIÓN REQUERIDA!
     * Debes agregar este Label en tu archivo "SeleccionPagoMenu.fxml".
     * Ejemplo: <Label fx:id="idConceptos" layoutX="50" layoutY="150" />
     */
    @FXML
    private Label idConceptos;

    /**
     * CAMBIO: El método 'muestra' ahora acepta una lista de conceptos en lugar de un 'Alumno'.
     * @param control El controlador de esta ventana.
     * @param total El monto total a pagar.
     * @param conceptos La lista de conceptos que se mostrarán en la interfaz.
     */
    public void muestra(ControlSeleccionPagoMenu control, int total, List<String> conceptos) {
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

            // Se actualizan los datos en la ventana
            idTotal.setText("$" + total);

            // CAMBIO: Se muestran los conceptos a pagar en el nuevo Label.
            // Se usa String.join para unir los elementos de la lista con un salto de línea.
            if (idConceptos != null) {
                idConceptos.setText(String.join("\n", conceptos));
            } else {
                System.out.println("ADVERTENCIA: El Label 'idConceptos' no fue encontrado en el FXML.");
            }

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