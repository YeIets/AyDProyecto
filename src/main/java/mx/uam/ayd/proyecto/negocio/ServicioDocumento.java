package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.datos.DocumentoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
/**
 * Servicio relacionado con los documentos
 *
 */
public class ServicioDocumento {
	
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
			documentos.add(documento)
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

		if (documento.getDireccion() == null || documento.getDireccion().isBlank()) {
			log.info("El documento tiene una direccion nula o vacia : " + documento.getDireccion());
			return false;
		}

		Path ruta = Paths.get(documento.getDireccion());
		return Files.exists(ruta) && Files.isRegularFile(ruta);

	}

}
