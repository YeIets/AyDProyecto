package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioAlumno;
import mx.uam.ayd.proyecto.negocio.ServicioDocumento;
import mx.uam.ayd.proyecto.negocio.ServicioNotificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos.VentanaBuscarDocumentos.AlumnoDocumentoRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    private ServicioAlumno servicioAlumno; // <-- Se usa el servicio correcto

    public void inicia() {
        ventana.muestra(this);
        buscarAlumnos(""); // Cargar todos los alumnos al inicio
    }

    public void buscarAlumnos(String nombre) {
        // CORREGIDO: Llama al ServicioAlumno para la búsqueda
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

    public void descargarDocumento(AlumnoDocumentoRow alumno) {
        System.out.println("Iniciando descarga para: " + alumno.nombreProperty().get());
        // Lógica para descargar...
    }

    public void notificarPadre(String nombreAlumno, String apellidoAlumno) {

        Alumno alumno = servicioAlumno.recuperarAlumnoPorNombreYApellido(nombreAlumno, apellidoAlumno);
        Padre padre = alumno.getPadre();
        servicioNotificacion.notificarPadre(padre, alumno, "Se necesitan actualizar documentos");

    }

    // ✅ MÉTODO MODIFICADO PARA CERRAR LA VENTANA
    public void regresar() {
        System.out.println("Cerrando ventana de búsqueda de documentos.");
        ventana.cierra(); // Llama al método de la ventana para que se cierre
    }
}