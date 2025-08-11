package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class VentanaBuscarDocumentos {

    private Stage stage;
    private ControlBuscarDocumentos control;

    // Componentes del FXML
    @FXML private TextField textFieldBusqueda;
    @FXML private TableView<AlumnoDocumentoRow> tablaEstudiantes;
    @FXML private TableColumn<AlumnoDocumentoRow, String> columnaEstudiante;
    @FXML private TableColumn<AlumnoDocumentoRow, Boolean> columnaActa;
    @FXML private TableColumn<AlumnoDocumentoRow, Boolean> columnaCurp;
    @FXML private TableColumn<AlumnoDocumentoRow, Boolean> columnaCertificado;
    @FXML private TableColumn<AlumnoDocumentoRow, Boolean> columnaDomicilio;
    @FXML private TableColumn<AlumnoDocumentoRow, Void> columnaDescargar;
    @FXML private TableColumn<AlumnoDocumentoRow, Void> columnaNotificar;
    @FXML private Button idvolver;

    public void muestra(ControlBuscarDocumentos control) {
        this.control = control;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasAdministrativo/BuscarDocumentos.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Documentos");

            configurarVentana();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configurarVentana() {
        // Listener para que la búsqueda se active al escribir
        textFieldBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            control.buscarAlumnos(newValue);
        });

        idvolver.setOnAction(e -> control.regresar());

        // --- Configuración de la Tabla ---

        // 1. Conectar columnas con las propiedades del objeto AlumnoDocumentoRow
        columnaEstudiante.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaActa.setCellValueFactory(cellData -> cellData.getValue().tieneActaProperty());
        columnaCurp.setCellValueFactory(cellData -> cellData.getValue().tieneCurpProperty());
        columnaCertificado.setCellValueFactory(cellData -> cellData.getValue().tieneCertificadoProperty());
        columnaDomicilio.setCellValueFactory(cellData -> cellData.getValue().tieneDomicilioProperty());

        // 2. Personalizar celdas para mostrar íconos y botones
        configurarCeldaConIcono(columnaActa);
        configurarCeldaConIcono(columnaCurp);
        configurarCeldaConIcono(columnaCertificado);
        configurarCeldaConIcono(columnaDomicilio);
        configurarCeldaConBoton(columnaDescargar, "⬇️", "button-descargar");
        configurarCeldaConBoton(columnaNotificar, "Notificar", "button-notificar");
    }

    public void llenaTabla(List<AlumnoDocumentoRow> alumnos) {
        tablaEstudiantes.setItems(FXCollections.observableArrayList(alumnos));
    }

    // --- Métodos de ayuda para personalizar la tabla ---

    private void configurarCeldaConIcono(TableColumn<AlumnoDocumentoRow, Boolean> columna) {
        columna.setCellFactory(param -> new TableCell<>() {
            private final Label label = new Label();
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item ? "📄" : "❌"); // Muestra ícono si tiene el documento
                    setGraphic(label);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }

    private void configurarCeldaConBoton(TableColumn<AlumnoDocumentoRow, Void> columna, String textoBoton, String estilo) {
        columna.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button(textoBoton);
            {
                btn.getStyleClass().add(estilo); // Para darle estilos CSS si quieres
                btn.setOnAction(event -> {
                    AlumnoDocumentoRow alumno = getTableView().getItems().get(getIndex());
                    if ("Notificar".equals(textoBoton)) {
                        control.notificarAlumno(alumno);
                    } else {
                        control.descargarDocumento(alumno);
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
                setAlignment(Pos.CENTER);
            }
        });
    }


    // Este método ya está correctamente implementado en tu archivo:
    public void cierra() {
        stage.close();
    }

    /**
     * Clase interna para representar los datos de una fila en la tabla.
     */
    public static class AlumnoDocumentoRow {
        private final SimpleStringProperty nombre;
        private final SimpleBooleanProperty tieneActa;
        private final SimpleBooleanProperty tieneCurp;
        private final SimpleBooleanProperty tieneCertificado;
        private final SimpleBooleanProperty tieneDomicilio;

        public AlumnoDocumentoRow(String nombre, boolean acta, boolean curp, boolean cert, boolean dom) {
            this.nombre = new SimpleStringProperty(nombre);
            this.tieneActa = new SimpleBooleanProperty(acta);
            this.tieneCurp = new SimpleBooleanProperty(curp);
            this.tieneCertificado = new SimpleBooleanProperty(cert);
            this.tieneDomicilio = new SimpleBooleanProperty(dom);
        }

        public SimpleStringProperty nombreProperty() { return nombre; }
        public SimpleBooleanProperty tieneActaProperty() { return tieneActa; }
        public SimpleBooleanProperty tieneCurpProperty() { return tieneCurp; }
        public SimpleBooleanProperty tieneCertificadoProperty() { return tieneCertificado; }
        public SimpleBooleanProperty tieneDomicilioProperty() { return tieneDomicilio; }
    }
}