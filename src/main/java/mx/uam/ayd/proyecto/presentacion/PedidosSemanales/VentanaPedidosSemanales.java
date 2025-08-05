package mx.uam.ayd.proyecto.presentacion.PedidosSemanales;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import mx.uam.ayd.proyecto.negocio.ServicioPago;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;

@Component
public class VentanaPedidosSemanales implements Initializable {

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

    @Autowired
    private ServicioPago servicioPago;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarTotalesPorDia("Lunes", idLunesPedidos, idLunesPagados, idLunesPendientes);
        cargarTotalesPorDia("Martes", idMartesPedidos, idMartesPagados, idMartesPendientes);
        cargarTotalesPorDia("Miércoles", idMiercolesPedidos, idMiercolesPagados, idMiercolesPendientes);
        cargarTotalesPorDia("Jueves", idJuevesPedidos, idJuevesPagados, idJuevesPendientes);
        cargarTotalesPorDia("Viernes", idViernesPedidos, idViernesPagados, idViernesPendientes);
    }

    private void cargarTotalesPorDia(String dia, Label lblTotal, Label lblPagados, Label lblPendientes) {
        List<Pago> todos = servicioPago.recuperaPagosMenuPorDia(dia);
        List<Pago> pagados = servicioPago.recuperaPagosMenuPorDiaEstado(dia, "Pagado");
        List<Pago> pendientes = servicioPago.recuperaPagosMenuPorDiaEstado(dia, "Pendiente");

        lblTotal.setText(String.valueOf(todos.size()));
        lblPagados.setText(String.valueOf(pagados.size()));
        lblPendientes.setText(String.valueOf(pendientes.size()));
    }

    @FXML
    private void handleLunesClick(MouseEvent event) {
        System.out.println("Clic en pedidos de Lunes");
        // Aquí puedes llamar a otra ventana o mostrar detalles
    }

    @FXML
    private void handleMartesClick(MouseEvent event) {
        System.out.println("Clic en pedidos de Martes");
    }

    @FXML
    private void handleMiercolesClick(MouseEvent event) {
        System.out.println("Clic en pedidos de Miércoles");
    }

    @FXML
    private void handleJuevesClick(MouseEvent event) {
        System.out.println("Clic en pedidos de Jueves");
    }

    @FXML
    private void handleViernesClick(MouseEvent event) {
        System.out.println("Clic en pedidos de Viernes");
    }
}
