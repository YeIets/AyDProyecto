package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.AlumnoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAlumno {

    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Busca alumnos cuyo nombre contenga el texto de búsqueda.
     * No distingue entre mayúsculas y minúsculas.
     * @param nombre El texto a buscar en el nombre del alumno.
     * @return Una lista de alumnos que coinciden con la búsqueda.
     */
    public List<Alumno> buscarAlumnosPorNombre(String nombre) {
        return alumnoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}