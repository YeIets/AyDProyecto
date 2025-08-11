package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Notificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import java.util.List;

/**
 * 
 * Repositorio para notificaciones
 * 
 *
 */

public interface NotificacionRepository extends CrudRepository <Notificacion, Long> {
	
	public Notificacion findByTitulo(String titulo);

	public List <Notificacion> findByDestinatario(Padre destinatario);

	public List <Notificacion> findByMatricula(String matricula);

}
