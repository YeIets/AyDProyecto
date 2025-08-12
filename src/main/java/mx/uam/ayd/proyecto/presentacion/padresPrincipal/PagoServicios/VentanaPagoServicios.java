package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoServicios;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VentanaPagoServicios {

    private Stage stage;
    private ControlPagoServicios control;

    // Componentes del FXML
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

    // NOTA: Agrega este botón en tu archivo FXML
    @FXML
    private Button btnPagar;

    private int totalAcumulado = 0;

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
            stage.show();
            
            // Configura los listeners para los checkboxes y el botón de pagar
            configurarControles();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configurarControles() {
        // Cada vez que un checkbox cambia, se llama al método para recalcular el total
        idCheckInscripcion.setOnAction(e -> actualizarTotal());
        idCheckColegiatura.setOnAction(e -> actualizarTotal());
        idCheckUniforme.setOnAction(e -> actualizarTotal());
        idCheckLibros.setOnAction(e -> actualizarTotal());

        // El botón "Pagar" le pasa el total acumulado al controlador
        btnPagar.setOnAction(e -> control.procederAlPago(totalAcumulado, getServiciosSeleccionados()));
    }

    /**
     * Este método se ejecuta cada vez que se hace clic en un checkbox.
     * Recalcula el total basado en las selecciones actuales.
     */
    private void actualizarTotal() {
        totalAcumulado = 0;
        if (idCheckInscripcion.isSelected()) {
            totalAcumulado += 5000;
        }
        if (idCheckColegiatura.isSelected()) {
            totalAcumulado += 2000;
        }
        if (idCheckUniforme.isSelected()) {
            totalAcumulado += 1500;
        }
        if (idCheckLibros.isSelected()) {
            totalAcumulado += 950;
        }
        idTotal.setText("$" + totalAcumulado);
    }

    public List<String> getServiciosSeleccionados() {
        List<String> serviciosSeleccionados = new ArrayList<>();

        if (idCheckColegiatura.isSelected()) serviciosSeleccionados.add("Colegiatura");
        if (idCheckInscripcion.isSelected()) serviciosSeleccionados.add("Inscripcion");
        if (idCheckLibros.isSelected()) serviciosSeleccionados.add("Libros");
        if (idCheckUniforme.isSelected()) serviciosSeleccionados.add("Uniforme");

        return serviciosSeleccionados;
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}