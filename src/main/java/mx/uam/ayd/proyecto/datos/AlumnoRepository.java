package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;

/**
 * 
 * Repositorio para alumnos
 * 
 *
 */

public interface AlumnoRepository extends CrudRepository <Alumno, Long> {
	
	public Alumno findByNombreAndApellido(String nombre, String apellido);

	public Alumno findByMatricula(String matricula);

}
