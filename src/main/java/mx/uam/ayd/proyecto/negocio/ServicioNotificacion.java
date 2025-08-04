package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.uam.ayd.proyecto.datos.NoticacionRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Notificacion;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;

@Service
/**
 * Servicio relacionado con las notificaciones
 *
 */
public class ServicioNoitificacion {
	
	private final NoticacionRepository noticacionRepository;
	
	@Autowired
	public ServicioNoitificacion(NoticacionRepository noticacionRepository) {
		this.noticacionRepository = noticacionRepository;
	}
	
	//Regresa notificaciones asociadas a un padre, si no tiene notificaciones asociadas regresa una lista vacia
	public List <Notificacion> recuperaNotificaciones(Padre padre) {
		return noticacionRepository.findByDestinatario(padre);
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

}
