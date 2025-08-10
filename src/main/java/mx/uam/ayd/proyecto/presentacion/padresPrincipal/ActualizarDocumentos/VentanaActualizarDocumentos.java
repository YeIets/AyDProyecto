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
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VentanaActualizarDocumentos {

    private Stage stage;
    private ControlActualizarDocumentos control;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasPadre/ActualizarDocumentos/ActualizarDocumentos.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Actualización de Documentos");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void muestraMensajeError(String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error al subir archivo");
        alerta.setHeaderText("El archivo " + " no se pudo subir"); // puedes poner un encabezado si quieres
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void muestraMensajeExito(String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Archivo subido Exitosamente");
        alerta.setHeaderText("Archivo subido Exitosamente"); // opcional, puedes agregar un encabezado si lo deseas
        alerta.setContentText("Se subio el archivo " + mensaje);
        alerta.showAndWait();
    }


    public Stage getStage() {
        return stage;
    }

    @FXML
    public void handleSubirDocumento(ActionEvent event){

        Button boton = (Button) event.getSource();
        String documento = boton.getId();

        control.subirDocumento(documento);

    }

    @FXML
    public void handleCerrar() {
        if (stage != null) {
            stage.close();
        }
    }
}