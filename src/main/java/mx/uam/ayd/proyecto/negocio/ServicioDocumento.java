package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.AlumnoRepository;
import mx.uam.ayd.proyecto.datos.DocumentoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
/**
 * Servicio relacionado con los documentos
 *
 */
public class ServicioDocumento {

	private static final Logger log = LoggerFactory.getLogger(ServicioUsuario.class);
	private final DocumentoRepository documentoRepository;
	private final AlumnoRepository alumnoRepository;
	
	@Autowired
	public ServicioDocumento(DocumentoRepository documentoRepository, AlumnoRepository alumnoRepository) {
		this.documentoRepository = documentoRepository;
		this.alumnoRepository = alumnoRepository;
	}
	

	//Recupera todos los documentos
	public List <Documento> recuperaDocumentos() {
		List <Documento> documentos = new ArrayList<>();
		
		for(Documento documento:documentoRepository.findAll()) {
			documentos.add(documento);
		}
				
		return documentos;
	}

	//Regresa los alumnos que tengan exactamente 5 documentos en su lista de documentos
	public List <Alumno> validarDocumentos(List <Alumno> alumnos) {
		List <Alumno> alumnosConDocumentos = new ArrayList<>();
		
		for(Alumno alumno : alumnos){
			if (alumno.getDocumentos().size() == 5) {
				alumnosConDocumentos.add(alumno);
			}
		}

		return alumnosConDocumentos;
	}

	// Valida si la direccion del documento es valida o no
	// Regresa true si lo es y false si no es valida o si es una ruta vacia
	public boolean validarDireccion(Documento documento){

		if (documento.getDireccionArchivo() == null || documento.getDireccionArchivo().isBlank()) {
			log.info("El documento tiene una direccion nula o vacia : " + documento.getDireccionArchivo());
			return false;
		}

		Path ruta = Paths.get(documento.getDireccionArchivo());
		return Files.exists(ruta) && Files.isRegularFile(ruta);

	}

}
