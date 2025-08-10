package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.AdministrativoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Administrativo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * Servicio relacionado con los alumnos
 *
 */
public class ServicioAdministrativo {
	
	private final AdministrativoRepository administrativoRepository;
	
	@Autowired
	public ServicioAdministrativo(AdministrativoRepository administrativoRepository) {
		this.administrativoRepository = administrativoRepository;
	}

	//Persiste un administrativo en la base de datos
	public Administrativo agregarAdministrativo(String nombre, String password){
		Administrativo admin = new Administrativo(nombre, password);
		return administrativoRepository.save(admin);
	}
}
