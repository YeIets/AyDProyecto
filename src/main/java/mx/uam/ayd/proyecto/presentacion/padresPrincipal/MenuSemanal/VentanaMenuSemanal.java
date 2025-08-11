package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal;

import java.io.IOException;
import java.util.Map;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu.ControlSeleccionMenu;

@Component
public class VentanaMenuSemanal {

    private Stage stage;
    private ControlMenuSemanal control;

    @FXML private Label idLunesComida;
    @FXML private Label idLunesAgua;
    @FXML private Label idLunesFruta;
    @FXML private Label idLunesGelatina;

    @FXML private Label idMartesComida;
    @FXML private Label idMartesAgua;
    @FXML private Label idMartesFruta;
    @FXML private Label idMartesGelatina;

    @FXML private Label idMiercolesComida;
    @FXML private Label idMiercolesAgua;
    @FXML private Label idMiercolesFruta;
    @FXML private Label idMiercolesGelatina;

    @FXML private Label idJuevesComida;
    @FXML private Label idJuevesAgua;
    @FXML private Label idJuevesFruta;
    @FXML private Label idJuevesGelatina;

    @FXML private Label idViernesComida;
    @FXML private Label idViernesAgua;
    @FXML private Label idViernesFruta;
    @FXML private Label idViernesGelatina;

    @FXML private Button idAceptar;
    @FXML private Button idRegresar;

    @Autowired
    private ControlSeleccionMenu controlSeleccionMenu; // ← se inyecta el controlador de la siguiente ventana

    /**
     * Muestra la ventana y carga los menús
     */
    public void muestra(ControlMenuSemanal control, Map<String, String[]> menuPorDia) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control, menuPorDia));
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
            stage.show();

            // Asignar menú recibido
            actualizarMenu(menuPorDia);

            // Eventos
            idAceptar.setOnAction(e -> {
                cerrar();
                controlSeleccionMenu.inicia(); // ← abre la siguiente ventana
            });

            idRegresar.setOnAction(e -> stage.close());

        } catch (IOException e) {
            System.err.println("Error al cargar PadreMenuSemanal.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Llena los labels del menú con los datos recibidos
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
