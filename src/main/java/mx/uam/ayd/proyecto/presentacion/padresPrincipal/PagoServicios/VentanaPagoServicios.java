package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author      Nombre del autor(es)
 * @since       1.0
 *
 * Módulo de presentación que gestiona la interfaz gráfica para el pago de servicios.
 * Permite al usuario seleccionar diversos conceptos de pago y visualiza el total acumulado.
 */
@Component
public class VentanaPagoServicios {

    private Stage stage;
    private ControlPagoServicios control;

    // Constantes para los montos de los servicios
    private static final int MONTO_INSCRIPCION = 5000;
    private static final int MONTO_COLEGIATURA = 2000;
    private static final int MONTO_UNIFORME = 1500;
    private static final int MONTO_LIBROS = 950;

    // Componentes de la interfaz gráfica definidos en el archivo FXML
    @FXML
    private CheckBox idCheckInscripcion;
    @FXML
    private CheckBox idCheckColegiatura;
    @FXML
    private CheckBox idCheckUniforme;
    @FXML
    private CheckBox idCheckLibros;
    @FXML
    private Label idTotal;
    @FXML
    private Button btnPagar;

    private int totalAcumulado = 0;

    /**
     * Muestra y configura la ventana principal de pago de servicios.
     *
     * @param control Instancia del controlador que gestionará las acciones de la ventana.
     */
    public void muestra(ControlPagoServicios control) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/PagoServicios/PagoServicios.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Portal de Pagos");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);

            // Inicializa los listeners para los componentes de la interfaz.
            configurarControles();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            muestraDialogoDeAdvertencia("Error al cargar la interfaz de pago de servicios.");
        }
    }

    /**
     * Vincula los eventos de la interfaz (clics en checkboxes y botones) con
     * los métodos correspondientes para manejar la lógica de la aplicación.
     */
    private void configurarControles() {
        // Asigna la acción de actualizar el total a cada checkbox.
        idCheckInscripcion.setOnAction(e -> actualizarTotal());
        idCheckColegiatura.setOnAction(e -> actualizarTotal());
        idCheckUniforme.setOnAction(e -> actualizarTotal());
        idCheckLibros.setOnAction(e -> actualizarTotal());

        // Asigna la acción de proceder al pago al botón correspondiente.
        btnPagar.setOnAction(e -> control.procederAlPago(totalAcumulado, getServiciosSeleccionados()));
    }

    /**
     * Calcula el monto total a pagar basándose en los checkboxes que están seleccionados
     * y actualiza la etiqueta de total en la interfaz.
     */
    private void actualizarTotal() {
        totalAcumulado = 0;
        if (idCheckInscripcion.isSelected()) {
            totalAcumulado += MONTO_INSCRIPCION;
        }
        if (idCheckColegiatura.isSelected()) {
            totalAcumulado += MONTO_COLEGIATURA;
        }
        if (idCheckUniforme.isSelected()) {
            totalAcumulado += MONTO_UNIFORME;
        }
        if (idCheckLibros.isSelected()) {
            totalAcumulado += MONTO_LIBROS;
        }
        idTotal.setText("$" + totalAcumulado);
    }

    /**
     * Construye y retorna una lista de cadenas con los nombres de los
     * servicios que han sido seleccionados por el usuario.
     *
     * @return Una lista con los nombres de los servicios seleccionados.
     */
    public List<String> getServiciosSeleccionados() {
        List<String> serviciosSeleccionados = new ArrayList<>();

        if (idCheckInscripcion.isSelected()) serviciosSeleccionados.add("Inscripción");
        if (idCheckColegiatura.isSelected()) serviciosSeleccionados.add("Colegiatura");
        if (idCheckUniforme.isSelected()) serviciosSeleccionados.add("Uniforme");
        if (idCheckLibros.isSelected()) serviciosSeleccionados.add("Libros");

        return serviciosSeleccionados;
    }

    /**
     * Despliega una ventana de diálogo modal con un mensaje de advertencia.
     *
     * @param mensaje El texto que se mostrará en el diálogo.
     */
    public void muestraDialogoDeAdvertencia(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initOwner(stage);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    /**
     * Cierra la ventana (el Stage de JavaFX).
     */
    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}