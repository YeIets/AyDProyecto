package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import java.time.LocalDate;

public interface DocumentoRepository extends CrudRepository <Documento, Long> {

    public Documento findByTipo(String tipo);

    // Este nombre de método ahora coincide con el campo "fechaDeSubida" de la entidad Documento
    public Documento findByFechaDeSubida(LocalDate fechaDeSubida);

    public Documento findByNombre(String nombre);

}