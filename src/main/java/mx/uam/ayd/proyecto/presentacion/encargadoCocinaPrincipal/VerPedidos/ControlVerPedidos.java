package mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.VerPedidos;

import jakarta.annotation.PostConstruct;
import javafx.fxml.FXML;
import mx.uam.ayd.proyecto.presentacion.encargadoCocinaPrincipal.ControlEncargadoCocinaPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlVerPedidos {

    @Autowired
    private VentanaVerPedidos ventana;


    private ControlEncargadoCocinaPrincipal controlPrincipal;

    @PostConstruct
    public void init() {
        ventana.setControl(this);
    }

    public void inicia(ControlEncargadoCocinaPrincipal controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
        ventana.muestra();
    }

    // Método para ser llamado cuando se regresa de una sub-ventana
    public void regresarAVerPedidos() {
        ventana.muestra();
    }

    @FXML
    public void handleRegresar() {
        ventana.oculta();
        if (controlPrincipal != null) {
            // Regresa a la ventana principal del encargado de cocina
          controlPrincipal.inicia();
        }
    }

}

