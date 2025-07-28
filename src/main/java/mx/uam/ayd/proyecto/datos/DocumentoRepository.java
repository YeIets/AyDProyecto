package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import java.LocalDate;

/**
 * 
 * Repositorio para documentos
 * 
 *
 */

public interface DocumentoRepository extends CrudRepository <Documento, Long> {
	
	public Documento findByTipo(String tipo);

	public Documento findByFechaDeSubida(LocalDate fechaDeSubida);

}
