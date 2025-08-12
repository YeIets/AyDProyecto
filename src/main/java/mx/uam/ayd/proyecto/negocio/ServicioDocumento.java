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
                log.info("Directorio de subidas creado en: {}", uploadPath.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de subidas", e);
        }
    }

    /**
     * CAMBIO: El método ahora recibe el Alumno al que pertenece el documento.
     * Sube un archivo, crea la entidad Documento y la vincula con el Alumno.
     *
     * @param archivo El archivo seleccionado por el usuario.
     * @param tipoDeDocumento El tipo de documento (ej. "CURP").
     * @param alumno El alumno al que se le asociará el documento.
     * @return El documento creado y guardado.
     * @throws IOException Si ocurre un error al copiar el archivo.
     */
    public Documento subirDocumento(File archivo, String tipoDeDocumento, Alumno alumno) throws IOException {
        // 1. Copia el archivo físico al directorio de subidas
        Path destino = Paths.get(UPLOAD_DIR).resolve(archivo.getName());
        Files.copy(archivo.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        log.info("Archivo copiado a: {}", destino.toAbsolutePath());

        // 2. Crea la nueva entidad Documento
        Documento nuevoDocumento = new Documento(
                archivo.getName(),
                tipoDeDocumento,
                destino.toString(),
                LocalDate.now()
        );

        // 3. Vincula el nuevo documento con el alumno
        alumno.agregarDocumento(nuevoDocumento);

        // 4. Guarda el alumno. Gracias a la relación en cascada,
        //    el nuevo documento se guardará y vinculará automáticamente.
        alumnoRepository.save(alumno);

        return nuevoDocumento;
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
        for (Alumno alumno : alumnos) {
            if (alumno.getDocumentos().size() == 5) {
                alumnosConDocumentos.add(alumno);
            }
        }
        return alumnosConDocumentos;
    }



    public boolean validarDireccion(Documento documento) {
        if (documento.getRuta() == null || documento.getRuta().isBlank()) {
            log.info("El documento tiene una ruta nula o vacía.");
            return false;
        }
        Path ruta = Paths.get(documento.getRuta());
        return Files.exists(ruta) && Files.isRegularFile(ruta);
    }

    public List<Alumno> buscarAlumnosPorNombre(String nombre) {
        return alumnoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}