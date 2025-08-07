package mx.uam.ayd.proyecto.presentacion.padresPrincipal.ActualizarDocumentos;

import javafx.stage.FileChooser;
import mx.uam.ayd.proyecto.negocio.ServicioDocumento;
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

    /**
     * Método principal para iniciar la HU-05.
     * Muestra la ventana de actualización de documentos.
     */
    public void inicia() {
        ventana.muestra(this);
    }

    /**
     * Inicia el proceso de subida de un archivo.
     * Es llamado por los botones en la ventana.
     * @param tipoDeDocumento Una cadena que identifica el tipo de documento.
     */
    public void iniciaSubida(String tipoDeDocumento) {
        System.out.println("Iniciando subida para: " + tipoDeDocumento);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona tu " + tipoDeDocumento);

        // Filtro para que solo se puedan seleccionar archivos PDF
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File archivoSeleccionado = fileChooser.showOpenDialog(ventana.getStage());

        if (archivoSeleccionado != null) {
            // Si el usuario seleccionó un archivo, intentamos subirlo
            try {
                servicioDocumento.subirDocumento(archivoSeleccionado);
                ventana.muestraDialogoExito(archivoSeleccionado.getName());
            } catch (IOException e) {
                ventana.muestraDialogoError("No se pudo guardar el archivo.");
                e.printStackTrace();
            }
        }
    }
}