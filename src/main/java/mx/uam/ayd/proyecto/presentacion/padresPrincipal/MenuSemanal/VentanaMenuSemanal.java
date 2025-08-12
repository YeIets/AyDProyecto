package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal;

import java.io.IOException;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import org.springframework.stereotype.Component;

@Component
public class VentanaMenuSemanal {

    private Stage stage;
    private ControlMenuSemanal control;

    @FXML private Label idLunesComida, idLunesAgua, idLunesFruta, idLunesGelatina;
    @FXML private Label idMartesComida, idMartesAgua, idMartesFruta, idMartesGelatina;
    @FXML private Label idMiercolesComida, idMiercolesAgua, idMiercolesFruta, idMiercolesGelatina;
    @FXML private Label idJuevesComida, idJuevesAgua, idJuevesFruta, idJuevesGelatina;
    @FXML private Label idViernesComida, idViernesAgua, idViernesFruta, idViernesGelatina;
    @FXML private Button idAceptar, idRegresar;

    // Campo para el Label del nombre del alumno (se configurará en el FXML al final)
    @FXML private Label idNombreAlumno;

    /**
     * Muestra la ventana y carga los menús para un alumno específico.
     * @param control El controlador de esta ventana.
     * @param menuPorDia El mapa con la información del menú.
     * @param alumno El alumno para el cual se muestra el menú.
     */
    public void muestra(ControlMenuSemanal control, Map<String, String[]> menuPorDia, Alumno alumno) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control, menuPorDia, alumno));
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/MenuSemanal/PadreMenuSemanal.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Menú Semanal");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);

            // Se actualiza el nombre del alumno en la interfaz
            if (idNombreAlumno != null) {
                idNombreAlumno.setText("Menú para: " + alumno.getNombreCompleto());
            }

            // Asignar menú recibido
            actualizarMenu(menuPorDia);

            // Eventos de los botones
            idAceptar.setOnAction(e -> control.elegirDias());
            idRegresar.setOnAction(e -> control.regresar());

            stage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar PadreMenuSemanal.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Llena los labels del menú con los datos recibidos del controlador.
     */
    private void actualizarMenu(Map<String, String[]> menuPorDia) {
        if (menuPorDia.containsKey("Lunes")) {
            String[] items = menuPorDia.get("Lunes");
            idLunesComida.setText(items[0]);
            idLunesAgua.setText(items[1]);
            idLunesFruta.setText(items[2]);
            idLunesGelatina.setText(items[3]);
        }

        if (menuPorDia.containsKey("Martes")) {
            String[] items = menuPorDia.get("Martes");
            idMartesComida.setText(items[0]);
            idMartesAgua.setText(items[1]);
            idMartesFruta.setText(items[2]);
            idMartesGelatina.setText(items[3]);
        }

        if (menuPorDia.containsKey("Miercoles")) {
            String[] items = menuPorDia.get("Miercoles");
            idMiercolesComida.setText(items[0]);
            idMiercolesAgua.setText(items[1]);
            idMiercolesFruta.setText(items[2]);
            idMiercolesGelatina.setText(items[3]);
        }

        if (menuPorDia.containsKey("Jueves")) {
            String[] items = menuPorDia.get("Jueves");
            idJuevesComida.setText(items[0]);
            idJuevesAgua.setText(items[1]);
            idJuevesFruta.setText(items[2]);
            idJuevesGelatina.setText(items[3]);
        }

        if (menuPorDia.containsKey("Viernes")) {
            String[] items = menuPorDia.get("Viernes");
            idViernesComida.setText(items[0]);
            idViernesAgua.setText(items[1]);
            idViernesFruta.setText(items[2]);
            idViernesGelatina.setText(items[3]);
        }
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}