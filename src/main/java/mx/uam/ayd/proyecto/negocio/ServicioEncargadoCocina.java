package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.EncargadoCocinaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.EncargadoCocina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
/**
 * Servicio relacionado con los alumnos
 *
 */
public class ServicioEncargadoCocina {
	
	private final EncargadoCocinaRepository encargadoCocinaRepository;
	
	@Autowired
	public ServicioEncargadoCocina(EncargadoCocinaRepository encargadoCocinaRepository) {
		this.encargadoCocinaRepository = encargadoCocinaRepository;
	}
	
	//Checa si NO es miercoles
	public boolean validaMiercoles(){
		return LocalDateTime.now().getDayOfWeek() == DayOfWeek.WEDNESDAY;
	}

	//Persiste un encargado en la base de datos
	public EncargadoCocina agregarEncargadoCocina(String nombre, String password){
		EncargadoCocina encargado = new EncargadoCocina(nombre, password);
		return encargadoCocinaRepository.save(encargado);
	}
}
