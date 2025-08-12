package mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class VentanaActualizarDocumentos {

    private Stage stage;
    private ControlActualizarDocumentos control;

    @FXML
    private Label idNombreAlumno;

    public void muestra(ControlActualizarDocumentos control, Alumno alumno) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/ActualizarDocumentos/ActualizarDocumentos.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();
            stage.setTitle("Actualización de Documentos");

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(600);
            stage.setHeight(420);

            if (idNombreAlumno != null) {
                idNombreAlumno.setText("Actualizando documentos para: " + alumno.getNombreCompleto());
            }

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void muestraMensajeError(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error al Subir Archivo");
        alerta.setHeaderText("Ocurrió un problema al intentar subir el archivo.");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void muestraMensajeExito(String nombreArchivo) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Éxito");
        alerta.setHeaderText("Archivo Subido Exitosamente");
        alerta.setContentText("Se subió el archivo: " + nombreArchivo);
        alerta.showAndWait();
    }

    public Stage getStage() {
        return stage;
    }

    // CAMBIO: Se reemplaza el método genérico por métodos específicos para cada botón.
    // Esto asegura que siempre se envíe el tipo de documento correcto y consistente.

    @FXML
    private void handleSubirActa() {
        control.subirDocumento("Acta de Nacimiento");
    }

    @FXML
    private void handleSubirCURP() {
        control.subirDocumento("CURP");
    }

    @FXML
    private void handleSubirDomicilio() {
        control.subirDocumento("Domicilio");
    }

    @FXML
    private void handleSubirCertificado() {
        control.subirDocumento("Certificado Médico");
    }

    @FXML
    public void handleCerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}