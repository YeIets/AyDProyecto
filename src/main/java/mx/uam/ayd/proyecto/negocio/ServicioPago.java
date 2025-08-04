package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.PagoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;

@Service
/**
 * Servicio relacionado con los alumnos
 *
 */
public class ServicioPago {
	
	private final PagoRepository pagoRepository;
	
	@Autowired
	public ServicioPago(PagoRepository pagoRepository) {
		this.pagoRepository = pagoRepository;
	}
	

	//Recupera los pagos con concepto "Menu Semanal" de un padre
	public boolean recuperarPagosDeMenuPadre(Padre padre){

		List<Pago> pagosMenu = new ArrayList<> ();

		for (Pago pago : pagoRepository.findByDestinatario(padre)) {
			if (pago.getConceptoDePago().equals("Menu Semanal")) {
				pagosMenu.add(pago);
			}
		}

		return pagosMenu.isEmpty();

	} 

	//Recuperar Pagos de Menu filtrando por dia 
	public List <Pago> recuperaPagosMenuPorDia(String dia){
		return pagoRepository.findByDiaAndEstado(dia)
	}

	//Recuperar Pagos de Menu filtrando por dia y estado
	public List <Pago> recuperaPagosMenuPorDiaEstado(String dia, String estado){
		return pagoRepository.findByDiaAndEstado(dia, estado)
	}

	//Genera una linea de captura para el pago en caja
	public Pago crearPagoCaja(float total, Padre padre, String diaElegido) {
		Pago pago = new Pago(padre, total, diaElegido);
		
		Pago persistido = pagoRepository.save(pago);

		if (persistido != null && persistido.getId() != null) {
			return persistido;
		} else return null;
	}



}
