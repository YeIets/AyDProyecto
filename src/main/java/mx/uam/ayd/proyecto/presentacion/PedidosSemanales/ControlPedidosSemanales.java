package mx.uam.ayd.proyecto.presentacion.PedidosSemanales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.ServicioPago;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;

@Component
public class ControlPedidosSemanales {

    @FXML private Label idLunesPedidos;
    @FXML private Label idLunesPagados;
    @FXML private Label idLunesPendientes;

    @FXML private Label idMartesPedidos;
    @FXML private Label idMartesPagados;
    @FXML private Label idMartesPendientes;

    @FXML private Label idMiercolesPedidos;
    @FXML private Label idMiercolesPagados;
    @FXML private Label idMiercolesPendientes;

    @FXML private Label idJuevesPedidos;
    @FXML private Label idJuevesPagados;
    @FXML private Label idJuevesPendientes;

    @FXML private Label idViernesPedidos;
    @FXML private Label idViernesPagados;
    @FXML private Label idViernesPendientes;

    private Stage stage;

    @Autowired
    private ServicioPago servicioPago;

    public void inicia(Stage stage) {
        this.stage = stage;
        cargarDatosPedidos();
        stage.show();
    }

    private void cargarDatosPedidos() {
        cargarDatosPorDia("Lunes", idLunesPedidos, idLunesPagados, idLunesPendientes);
        cargarDatosPorDia("Martes", idMartesPedidos, idMartesPagados, idMartesPendientes);
        cargarDatosPorDia("Miércoles", idMiercolesPedidos, idMiercolesPagados, idMiercolesPendientes);
        cargarDatosPorDia("Jueves", idJuevesPedidos, idJuevesPagados, idJuevesPendientes);
        cargarDatosPorDia("Viernes", idViernesPedidos, idViernesPagados, idViernesPendientes);
    }

    private void cargarDatosPorDia(String dia, Label total, Label pagados, Label pendientes) {
        List<Pago> listaTotal = servicioPago.recuperaPagosMenuPorDia(dia);
        List<Pago> listaPagados = servicioPago.recuperaPagosMenuPorDiaEstado(dia, "PAGADO");
        List<Pago> listaPendientes = servicioPago.recuperaPagosMenuPorDiaEstado(dia, "PENDIENTE");

        total.setText(String.valueOf(listaTotal.size()));
        pagados.setText(String.valueOf(listaPagados.size()));
        pendientes.setText(String.valueOf(listaPendientes.size()));
    }

    // Métodos para manejar clics (puedes redirigir a otra ventana desde aquí)
    @FXML
    private void handleLunesClick() {
        // abrir detalle de lunes
    }

    @FXML
    private void handleMartesClick() {
        // abrir detalle de martes
    }

    @FXML
    private void handleMiercolesClick() {
        // abrir detalle de miércoles
    }

    @FXML
    private void handleJuevesClick() {
        // abrir detalle de jueves
    }

    @FXML
    private void handleViernesClick() {
        // abrir detalle de viernes
    }
}
