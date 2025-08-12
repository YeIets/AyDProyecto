package mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos;

import javafx.stage.FileChooser;
import mx.uam.ayd.proyecto.negocio.ServicioDocumento;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Component
public class ControlActualizarDocumentos {

    @Autowired
    private VentanaActualizarDocumentos ventana;

    @Autowired
    private ServicioDocumento servicioDocumento;

    private Alumno alumnoSeleccionado;

    /**
     * Guarda el alumno y se lo pasa a la ventana para mostrarlo.
     * @param alumno El alumno cuyos documentos se van a actualizar.
     */
    public void inicia(Alumno alumno) {
        this.alumnoSeleccionado = alumno;
        // CAMBIO: Ahora pasamos el alumno a la ventana.
        ventana.muestra(this, alumno);
    }

    /**
     * Inicia el proceso de subida de un archivo.
     * @param tipoDeDocumento El tipo de documento a subir.
     */
    public void subirDocumento(String tipoDeDocumento) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona tu " + tipoDeDocumento);
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File archivoSeleccionado = fileChooser.showOpenDialog(ventana.getStage());

        if (archivoSeleccionado != null) {
            try {
                servicioDocumento.subirDocumento(archivoSeleccionado, tipoDeDocumento, this.alumnoSeleccionado);
                ventana.muestraMensajeExito(archivoSeleccionado.getName());
            } catch (IOException e) {
                ventana.muestraMensajeError("Ocurrió un error al guardar el archivo.");
                e.printStackTrace();
            }
        }
    }
}