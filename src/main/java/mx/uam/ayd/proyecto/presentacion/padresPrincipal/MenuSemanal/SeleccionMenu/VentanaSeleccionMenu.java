package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class VentanaSeleccionMenu {

    private Stage stage;
    private ControlSeleccionMenu control;

    @FXML private CheckBox checkLunes;
    @FXML private CheckBox checkMartes;
    @FXML private CheckBox checkMiercoles;
    @FXML private CheckBox checkJueves;
    @FXML private CheckBox checkViernes;

    @FXML private Label idNombreAlumno;

    public void muestra(ControlSeleccionMenu control, Alumno alumno) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/MenuSemanal/SeleccionMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Selección de Días del Menú");
            stage.setScene(new Scene(root));

            if (idNombreAlumno != null) {
                idNombreAlumno.setText("Selección para: " + alumno.getNombreCompleto());
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePagar() {
        int total = 0;
        int precioPorDia = 100;

        if (checkLunes.isSelected()) total += precioPorDia;
        if (checkMartes.isSelected()) total += precioPorDia;
        if (checkMiercoles.isSelected()) total += precioPorDia;
        if (checkJueves.isSelected()) total += precioPorDia;
        if (checkViernes.isSelected()) total += precioPorDia;

        if (total > 0) {
            control.irAVentanaPagos(total);
        } else {
            System.out.println("AVISO: No se ha seleccionado ningún día del menú.");
        }
    }

    /**
     * ✅ NUEVO MÉTODO: Añadido para manejar la acción del botón 'Volver'.
     * Delega la lógica de navegación al controlador.
     */
    @FXML
    public void handleVolver() {
        control.regresar();
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}