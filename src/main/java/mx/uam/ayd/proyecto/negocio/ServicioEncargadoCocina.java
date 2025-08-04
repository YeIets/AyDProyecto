package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.EncargadoCocinaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.EncargadoCocina;

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
