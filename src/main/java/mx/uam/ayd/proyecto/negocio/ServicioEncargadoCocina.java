package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.EncargadoCocinaRepository;
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

}
