package mx.uam.ayd.proyecto.presentacion.padresPrincipal.PadresPagosElegir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoMenuCaja.ControlPagoCajaMenu;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PagoMenuLinea.ControlPagoLineaMenu;
import mx.uam.ayd.proyecto.presentacion.padresPrincipal.PedidosSemanales.ControlSeleccionMenu;

@Component
public class ControlPadresPagosElegir {

    @Autowired
    private VentanaPadresPagosElegir ventana;

    @Autowired
    @Lazy // Para romper el primer ciclo
    private ControlPagoLineaMenu controlPagoLineaMenu;

    @Autowired
    @Lazy // <-- APLICA LA SOLUCIÓN AQUÍ TAMBIÉN
    private ControlPagoCajaMenu controlPagoCajaMenu;

    private ControlSeleccionMenu controlSeleccionMenu;
    private int total;

    public void inicia(int total, ControlSeleccionMenu controlSeleccionMenu) {
        this.total = total;
        this.controlSeleccionMenu = controlSeleccionMenu;
        ventana.muestra(this, total);
    }

    public void irAPagoLinea() {
        ventana.cerrar();
        controlPagoLineaMenu.inicia(total);
    }

    public void irAPagoCaja() {
        ventana.cerrar();
        controlPagoCajaMenu.inicia(total);
    }

    public void regresar() {
        ventana.muestra(this, total);
    }

    public ControlSeleccionMenu getControlSeleccionMenu() {
        return controlSeleccionMenu;
    }

    public void cerrar() {
        ventana.cerrar();
    }
}