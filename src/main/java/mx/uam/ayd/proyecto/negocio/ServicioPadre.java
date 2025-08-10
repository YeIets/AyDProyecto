package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.PadreRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
/**
 * Servicio relacionado con los alumnos
 *
 */
public class ServicioPadre {
	
	private final PadreRepository padreRepository;
	
	@Autowired
	public ServicioPadre(PadreRepository padreRepository) {
		this.padreRepository = padreRepository;
	}
	
	//Recupera TODOS los padres
	public List <Padre> recuperaPadres() {
		List <Padre> padres = new ArrayList<>();
		
		for(Padre padre : padreRepository.findAll()) {
			padres.add(padre);
		}
				
		return padres;
	}

	//Persiste un padre en la base de datos
	public Padre agregarPadre(String nombre, String password){
		Padre padre = new Padre(nombre, password);
		return padreRepository.save(padre);
	}

	//Regresa True si esta registrado en la BD
	public boolean verificarPadreRegistrado(String correo, String password){
		Padre padre = padreRepository.findByCorreoAndPassword(correo,password);
		return padre != null;
	}

}
