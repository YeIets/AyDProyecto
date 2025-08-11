package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.NotificacionRepository;
import mx.uam.ayd.proyecto.datos.PadreRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Alumno;
import mx.uam.ayd.proyecto.negocio.modelo.Notificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
/**
 * Servicio relacionado con las notificaciones
 *
 */
public class ServicioNotificacion {

	private static final Logger log = LoggerFactory.getLogger(ServicioNotificacion.class);
	private final NotificacionRepository notificacionRepository;

	private final PadreRepository padreRepository;

	@Autowired
	public ServicioNotificacion(NotificacionRepository notificacionRepository, PadreRepository padreRepository) {
		this.notificacionRepository = notificacionRepository;
        this.padreRepository = padreRepository;
    }
	
	//Regresa notificaciones asociadas a un padre, si no tiene notificaciones asociadas regresa una lista vacia
	public List <Notificacion> recuperaNotificaciones(Padre padre) {
		return notificacionRepository.findByDestinatario(padre);
	}

	//Redacta y envia un correo de un pago a una direccion de correo 
	public boolean enviarCorreo(Pago pago, String correo){

		// Revisamos si el correo es valido (tendria que serlo)
		if (correo.isBlank()) {
			log.info("La direccion de correo es invalida");
			return false;
		}

		//Redactamos el correo
		String titulo = pago.getConceptoDePago();
		String cuerpo = pago.toString();

		log.info("Correo enviado al correo: " + correo);
		log.info("Titulo: " + titulo);
		log.info("Cuerpo: " + cuerpo);

		return true;
	}

	//Regresa True si el padre tiene notificaciones, False si no
	public boolean tieneNotificaciones(Padre padre){
		return !notificacionRepository.findByDestinatario(padre).isEmpty();
	}

    public boolean notificarPadre(Padre padre, Alumno alumno, String titulo) {

		if (padre == null || alumno == null){
			log.info("El padre o el alumno son invalidos");
			return false;
		}

		if (padre.getCorreo().isBlank()) {
			log.info("La direccion de correo es invalida");
			return false;
		}

		Notificacion notificacion = new Notificacion(
				titulo, LocalTime.now(), "Actualizacion de documentos", alumno.getMatricula(), padre);

		log.info("Notificacion enviada: " + notificacion);

		padre.agregaNotificacion(notificacion);
		padreRepository.save(padre);

		log.info("Destinatario: " + padre);

		return true;
    }
}
