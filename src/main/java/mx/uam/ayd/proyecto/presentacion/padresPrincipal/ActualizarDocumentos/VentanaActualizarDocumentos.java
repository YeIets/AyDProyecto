package mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaActualizarDocumentos {

    private Stage stage;
    private ControlActualizarDocumentos control;

    @FXML
    private Button idActa;

    @FXML
    private Button idCurp;

    @FXML
    private Button idDomicilio;

    @FXML
    private Button idMedico;

    @FXML
    private Button idVolver;

    // NOTA: Agrega un Label en tu FXML con este fx:id para mostrar mensajes
    @FXML
    private Label lblEstado;

    /**
     * Muestra la ventana. Este método es llamado por el controlador.
     * @param control El controlador de esta ventana.
     */
    public void muestra(ControlActualizarDocumentos control) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ActualizarDocumentos.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Actualización de Documentos");
            stage.show();

            // Conectamos los botones a las acciones del controlador
            configurarBotones();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configurarBotones() {
        // Cada botón llama al mismo método en el controlador, pero con un
        // parámetro diferente para identificar qué documento se está subiendo.
        idActa.setOnAction(e -> control.iniciaSubida("Acta de Nacimiento"));
        idCurp.setOnAction(e -> control.iniciaSubida("CURP"));
        idDomicilio.setOnAction(e -> control.iniciaSubida("Comprobante de Domicilio"));
        idMedico.setOnAction(e -> control.iniciaSubida("Certificado Médico"));

        idVolver.setOnAction(e -> cerrar());
    }

    /**
     * Muestra un mensaje de éxito en la etiqueta de estado.
     * @param nombreArchivo El nombre del archivo que se subió.
     */
    public void muestraDialogoExito(String nombreArchivo) {
        if(lblEstado != null) {
            lblEstado.setText("¡Éxito! Se subió el archivo: " + nombreArchivo);
        }
    }

    /**
     * Muestra un mensaje de error en la etiqueta de estado.
     * @param mensaje El error que ocurrió.
     */
    public void muestraDialogoError(String mensaje) {
        if(lblEstado != null) {
            lblEstado.setText("Error: " + mensaje);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void cerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}