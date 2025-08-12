package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import mx.uam.ayd.proyecto.datos.PadreRepository;
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
	private final PadreRepository padreRepository;
	
	@Autowired
	public ServicioPago(PagoRepository pagoRepository, PadreRepository padreRepository) {
		this.pagoRepository = pagoRepository;
        this.padreRepository = padreRepository;
    }
	

	//Recupera los pagos con concepto "Menu Semanal" de un padre
	public boolean recuperarPagosDeMenuPadre(Padre padre){

		List<Pago> pagosMenu = new ArrayList<> ();

		for (Pago pago : pagoRepository.findByTitular(padre)) {
			if (pago.getConceptoDePago().equals("Menu Semanal")) {
				pagosMenu.add(pago);
			}
		}

		return pagosMenu.isEmpty();

	} 

	//Recuperar Pagos de Menu filtrando por dia 
	public List <Pago> recuperaPagosMenuPorDia(String dia){
		return pagoRepository.findByDiaMenu(dia);
	}

	//Recuperar Pagos de Menu filtrando por dia y estado
	public List <Pago> recuperaPagosMenuPorDiaEstado(String dia, String estado){
		return pagoRepository.findByDiaMenuAndEstado(dia, estado);
	}

	//Genera el pago en caja para padre
	public void crearPagoDeMenuCaja(int total, Padre padre, String diaElegido) {

		Pago pago = new Pago(padre, total, diaElegido);
		pago.setConceptoDePago("Menu Semanal");
		pago.setMetodoDePago("Caja");
		pago.setEstado("Pendiente");
		padre.agregarPago(pago);
		padreRepository.save(padre);
	}

	public void crearPagoDeMenuEnLinea(int total, Padre padre, String diaElegido) {

		Pago pago = new Pago(padre, total, diaElegido);
		pago.setConceptoDePago("Menu Semanal");
		pago.setMetodoDePago("Pago En Linea");
		pago.setEstado("Pagado");
		padre.agregarPago(pago);
		padreRepository.save(padre);
	}


	public void crearPagoDeServiciosCaja(int total, Padre padre, String servicios) {
		Pago pago = new Pago(padre, total);
		pago.setConceptoDePago("Pago De Servicios");
		pago.setServicios(servicios);
		pago.setMetodoDePago("Caja");
		pago.setEstado("Pendiente");
		padre.agregarPago(pago);
		padreRepository.save(padre);
	}
	public void crearPagoDeServiciosEnLinea(int total, Padre padre, String servicios) {

		Pago pago = new Pago(padre, total);
		pago.setConceptoDePago("Pago De Servicios");
		pago.setServicios(servicios);
		pago.setMetodoDePago("Pago En Linea");
		pago.setEstado("Pagado");
		padre.agregarPago(pago);
		padreRepository.save(padre);
	}
}
