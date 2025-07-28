package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;
//import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import java.util.List;

/**
 * 
 * Repositorio para notificaciones
 * 
 *
 */

public interface PagoRepository extends CrudRepository <Pago, Long> {
	
	public List <Pago> findByConceptoDePago(String conceptoDePago);

	public List <Pago> findByEstado(String estado);

	public List <Pago> findByMatricula(int matricula);

}
