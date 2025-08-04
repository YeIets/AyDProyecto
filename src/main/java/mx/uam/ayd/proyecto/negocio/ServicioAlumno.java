package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.AlumnoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;

@Service
/**
 * Servicio relacionado con los alumnos
 *
 */
public class ServicioAlumno {
	
	private final AlumnoRepository alumnoRepository;
	
	@Autowired
	public ServicioAlumno(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}
	
	//Recupera TODOS los alumnos
	public List <Alumno> recuperaAlumnos() {
		List <Alumno> alumnos = new ArrayList<>();
		
		for(Alumno alumno : alumnoRepository.findAll()) {
			alumnos.add(alumno)
		}
				
		return alumnos;
	}

}
