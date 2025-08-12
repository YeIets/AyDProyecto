package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioAlumno;
import mx.uam.ayd.proyecto.negocio.ServicioDocumento;
import mx.uam.ayd.proyecto.negocio.ServicioNotificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos.VentanaBuscarDocumentos.AlumnoDocumentoRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ControlBuscarDocumentos {

    @Autowired
    private VentanaBuscarDocumentos ventana;

    @Autowired
    private ServicioDocumento servicioDocumento;

    @Autowired
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    private ServicioAlumno servicioAlumno;

    public void inicia() {
        ventana.muestra(this);
        buscarAlumnos("");
    }

    public void buscarAlumnos(String nombre) {
        List<Alumno> alumnosEncontrados = servicioAlumno.buscarAlumnosPorNombre(nombre);
        List<AlumnoDocumentoRow> filasParaTabla = new ArrayList<>();

        for (Alumno alumno : alumnosEncontrados) {
            boolean tieneActa = !alumno.getDocumentosPorTipo("Acta de Nacimiento").isEmpty();
            boolean tieneCurp = !alumno.getDocumentosPorTipo("CURP").isEmpty();
            boolean tieneCertificado = !alumno.getDocumentosPorTipo("Certificado Médico").isEmpty();
            boolean tieneDomicilio = !alumno.getDocumentosPorTipo("Domicilio").isEmpty();

            filasParaTabla.add(new AlumnoDocumentoRow(
                    alumno.getNombre(),
                    alumno.getApellido(),
                    tieneActa,
                    tieneCurp,
                    tieneCertificado,
                    tieneDomicilio
            ));
        }

        ventana.llenaTabla(filasParaTabla);
    }

    public void previsualizarDocumento(AlumnoDocumentoRow alumnoRow, String tipoDocumento) {
        Alumno alumno = servicioAlumno.recuperarAlumnoPorNombreYApellido(alumnoRow.getNombre(), alumnoRow.getApellido());
        if (alumno == null) { return; }

        List<Documento> documentos = alumno.getDocumentosPorTipo(tipoDocumento);
        if (documentos.isEmpty()) { return; }

        String rutaArchivo = documentos.get(0).getRuta();

        try {
            File archivo = new File(rutaArchivo);
            if (archivo.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            } else {
                log.error("No se pudo abrir el archivo: no existe o Desktop no es compatible.");
            }
        } catch (IOException e) {
            log.error("Error al intentar abrir el archivo: " + rutaArchivo, e);
        }
    }

    /**
     * Lógica completa para la descarga de documentos.
     * @param alumno El alumno seleccionado en la tabla.
     */
    public void descargarDocumento(AlumnoDocumentoRow alumno) {
        log.info("Iniciando proceso de descarga para: {}", alumno.getNombreCompleto());

        // 1. Recuperar el alumno completo y sus documentos
        Alumno alumnoCompleto = servicioAlumno.recuperarAlumnoPorNombreYApellido(alumno.getNombre(), alumno.getApellido());
        if (alumnoCompleto == null || alumnoCompleto.getDocumentos().isEmpty()) {
            log.warn("El alumno no tiene documentos para descargar.");
            mostrarAlerta("Sin Documentos", "Este alumno no tiene documentos disponibles para descargar.");
            return;
        }

        // 2. Crear una lista de los tipos de documentos disponibles para que el usuario elija
        List<String> tiposDeDocumentos = alumnoCompleto.getDocumentos()
                .stream()
                .map(Documento::getTipo)
                .collect(Collectors.toList());

        // 3. Mostrar un diálogo para que el usuario elija qué documento descargar
        ChoiceDialog<String> dialogo = new ChoiceDialog<>(tiposDeDocumentos.get(0), tiposDeDocumentos);
        dialogo.setTitle("Seleccionar Documento");
        dialogo.setHeaderText("Elige el documento que deseas descargar para " + alumno.getNombreCompleto());
        dialogo.setContentText("Documento:");

        Optional<String> resultado = dialogo.showAndWait();

        // 4. Si el usuario seleccionó un documento, proceder con la descarga
        resultado.ifPresent(tipoSeleccionado -> {
            // Encontrar el documento correspondiente al tipo seleccionado
            Documento docADescargar = alumnoCompleto.getDocumentosPorTipo(tipoSeleccionado).get(0);
            File archivoFuente = new File(docADescargar.getRuta());

            // 5. Abrir el diálogo "Guardar como..."
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Documento Como...");
            fileChooser.setInitialFileName(archivoFuente.getName()); // Sugerir el nombre original del archivo

            // Obtener el stage de la ventana actual para que el diálogo aparezca sobre ella
            File archivoDestino = fileChooser.showSaveDialog(ventana.getStage());

            // 6. Si el usuario eligió una ubicación, copiar el archivo
            if (archivoDestino != null) {
                try {
                    Files.copy(archivoFuente.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    log.info("Archivo descargado exitosamente en: {}", archivoDestino.getAbsolutePath());
                    mostrarAlerta("Descarga Completa", "El archivo se ha guardado correctamente.");
                } catch (IOException e) {
                    log.error("Error al copiar el archivo para la descarga.", e);
                    mostrarAlerta("Error de Descarga", "No se pudo guardar el archivo.");
                }
            } else {
                log.info("El usuario canceló la descarga.");
            }
        });
    }

    public void notificarPadre(String nombreAlumno, String apellidoAlumno) {
        // ... (lógica de notificación)
    }

    public void regresar() {
        log.info("Cerrando ventana de búsqueda de documentos.");
        ventana.cierra();
    }

    // Método de ayuda para mostrar alertas de información o error
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}