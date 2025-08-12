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

    /**
     * Inicia la ventana de búsqueda de documentos.
     */
    public void inicia() {
        ventana.muestra(this);
        buscarAlumnos("");
    }

    /**
     * Lógica para refrescar la tabla. Es llamado por la ventana
     * cuando se presiona el botón "Refrescar".
     */
    public void refrescarTabla() {
        log.info("Refrescando la tabla de documentos...");
        // Vuelve a buscar usando el último texto que el usuario haya escrito.
        buscarAlumnos(ventana.getBusquedaActual());
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

    public void descargarDocumento(AlumnoDocumentoRow alumno) {
        log.info("Iniciando proceso de descarga para: {}", alumno.getNombreCompleto());

        Alumno alumnoCompleto = servicioAlumno.recuperarAlumnoPorNombreYApellido(alumno.getNombre(), alumno.getApellido());
        if (alumnoCompleto == null || alumnoCompleto.getDocumentos().isEmpty()) {
            log.warn("El alumno no tiene documentos para descargar.");
            mostrarAlerta("Sin Documentos", "Este alumno no tiene documentos disponibles para descargar.");
            return;
        }

        List<String> tiposDeDocumentos = alumnoCompleto.getDocumentos()
                .stream()
                .map(Documento::getTipo)
                .collect(Collectors.toList());

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(tiposDeDocumentos.get(0), tiposDeDocumentos);
        dialogo.setTitle("Seleccionar Documento");
        dialogo.setHeaderText("Elige el documento que deseas descargar para " + alumno.getNombreCompleto());
        dialogo.setContentText("Documento:");

        Optional<String> resultado = dialogo.showAndWait();

        resultado.ifPresent(tipoSeleccionado -> {
            Documento docADescargar = alumnoCompleto.getDocumentosPorTipo(tipoSeleccionado).get(0);
            File archivoFuente = new File(docADescargar.getRuta());

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Documento Como...");
            fileChooser.setInitialFileName(archivoFuente.getName());

            File archivoDestino = fileChooser.showSaveDialog(ventana.getStage());

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
        Alumno alumno = servicioAlumno.recuperarAlumnoPorNombreYApellido(nombreAlumno, apellidoAlumno);
        if (alumno != null && alumno.getPadre() != null) {
            Padre padre = alumno.getPadre();
            servicioNotificacion.notificarPadre(padre, alumno, "Se necesitan actualizar documentos");
            log.info("Notificación enviada al padre del alumno: {} {}", nombreAlumno, apellidoAlumno);
        } else {
            log.warn("No se pudo notificar al padre del alumno: {} {}", nombreAlumno, apellidoAlumno);
        }
    }

    public void regresar() {
        log.info("Cerrando ventana de búsqueda de documentos.");
        ventana.cierra();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}