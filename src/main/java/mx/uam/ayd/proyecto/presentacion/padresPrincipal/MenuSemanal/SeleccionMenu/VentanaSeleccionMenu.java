package mx.uam.ayd.proyecto.presentacion.padresPrincipal.MenuSemanal.SeleccionMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaSeleccionMenu {

    private Stage stage;
    private ControlSeleccionMenu control;

    @FXML private CheckBox idLunesCheck;
    @FXML private CheckBox idMartesCheck;
    @FXML private CheckBox idMiercolesCheck;
    @FXML private CheckBox idJuvesCheck;
    @FXML private CheckBox idViernesCheck;

    @FXML private Label idTotal;

    private static final int PRECIO_POR_DIA = 35;

    /**
     * Muestra la ventana con su controlador asociado.
     */
    public void muestra(ControlSeleccionMenu control) {
        this.control = control;

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(control));
            return;
        }

        if (stage == null) {
            inicializaUI();
        }

        stage.show();
    }

    /**
     * Inicializa la interfaz desde el archivo FXML.
     */
    private void inicializaUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/MenuSemanal/SeleccionMenu.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            stage = new Stage();
            stage.setTitle("Seleccionar días del menú 4");
            stage.setScene(new Scene(root));
            stage.setResizable(false); 
            stage.setWidth(600);       
            stage.setHeight(420);      
            stage.show();
            
            actualizarTotal();

            configurarEventos();

        } catch (IOException e) {
            System.err.println("Error al cargar la ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Configura eventos de botones y checkboxes.
     */
    private void configurarEventos() {
        idLunesCheck.setOnAction(event -> actualizarTotal());
        idMartesCheck.setOnAction(event -> actualizarTotal());
        idMiercolesCheck.setOnAction(event -> actualizarTotal());
        idJuvesCheck.setOnAction(event -> actualizarTotal());
        idViernesCheck.setOnAction(event -> actualizarTotal());
    }

    /**
     * Calcula el total según los días seleccionados.
     */
    private int calcularTotal() {
        int total = 0;
        if (idLunesCheck.isSelected()) total += PRECIO_POR_DIA;
        if (idMartesCheck.isSelected()) total += PRECIO_POR_DIA;
        if (idMiercolesCheck.isSelected()) total += PRECIO_POR_DIA;
        if (idJuvesCheck.isSelected()) total += PRECIO_POR_DIA;
        if (idViernesCheck.isSelected()) total += PRECIO_POR_DIA;
        return total;
    }

    /**
     * Actualiza el label del total.
     */
    private void actualizarTotal() {
        int total = calcularTotal();
        idTotal.setText("$" + total);
    }

    /**
     * Cierra la ventana si está abierta.
     */
    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }

    public void mostrarMensajeError(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Error al pagar"); // puedes poner un encabezado si quieres
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    public void handlePagar(){
        if (calcularTotal() == 0) {
            mostrarMensajeError("Seleccione al menos un dia del menu");
        }
        else control.irAVentanaPagos(calcularTotal());
    }

    @FXML
    public void handleVolver(){
        cerrar();
    }

}