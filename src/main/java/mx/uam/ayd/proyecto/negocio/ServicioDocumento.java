package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.AlumnoRepository;
import mx.uam.ayd.proyecto.datos.DocumentoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.time.LocalDate;

@Service
public class ServicioDocumento {

    private static final Logger log = LoggerFactory.getLogger(ServicioDocumento.class);

    private static final String UPLOAD_DIR = "documentos_subidos";

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostConstruct
    public void init() {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
                log.info("Directorio de subidas creado en: " + uploadPath.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de subidas", e);
        }
    }

    public Documento subirDocumento(File archivo, String tipoDeDocumento) throws IOException {
        Path destino = Paths.get(UPLOAD_DIR).resolve(archivo.getName());
        Files.copy(archivo.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        log.info("Archivo copiado a: " + destino.toAbsolutePath());

        Documento nuevoDocumento = new Documento();
        nuevoDocumento.setNombre(archivo.getName());
        nuevoDocumento.setTipo(tipoDeDocumento);
        nuevoDocumento.setDireccionArchivo(destino.toString());
        nuevoDocumento.setFechaDeSubida(LocalDate.now());

        return documentoRepository.save(nuevoDocumento);
    }

    public List<String> recuperarNombresDeDocumentos() {
        return StreamSupport.stream(documentoRepository.findAll().spliterator(), false)
                .map(Documento::getNombre)
                .collect(Collectors.toList());
    }

    public Documento recuperarDocumentoPorNombre(String nombre) {
        return documentoRepository.findByNombre(nombre);
    }

    public List <Documento> recuperaDocumentos() {
        return (List<Documento>) documentoRepository.findAll();
    }



    public List <Alumno> validarDocumentos(List <Alumno> alumnos) {
        List <Alumno> alumnosConDocumentos = new ArrayList<>();
        for(Alumno alumno : alumnos){
            if (alumno.getDocumentos().size() == 5) {
                alumnosConDocumentos.add(alumno);
            }
        }
        return alumnosConDocumentos;
    }

    public boolean validarDireccion(Documento documento){
        if (documento.getDireccionArchivo() == null || documento.getDireccionArchivo().isBlank()) {
            log.info("El documento tiene una direccion nula o vacia : " + documento.getDireccionArchivo());
            return false;
        }
        Path ruta = Paths.get(documento.getDireccionArchivo());
        return Files.exists(ruta) && Files.isRegularFile(ruta);
    }


    /**
     * Busca alumnos cuyo nombre contenga el texto de búsqueda.
     * No distingue entre mayúsculas y minúsculas.
     * @param nombre El texto a buscar en el nombre del alumno.
     * @return Una lista de alumnos que coinciden con la búsqueda.
     */
    public List<Alumno> buscarAlumnosPorNombre(String nombre) {
        // CORRECCIÓN: Se usa la variable "alumnoRepository" (con 'a' minúscula)
        return alumnoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}