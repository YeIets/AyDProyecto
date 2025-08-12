package mx.uam.ayd.proyecto.presentacion.administrativoPrincipal.BuscarDocumentos;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
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
            log.error("Error al cargar el FXML para VentanaBuscarDocumentos", e);
        }
    }

    private void configurarVentana() {
        textFieldBusqueda.textProperty().addListener((observable, oldValue, newValue) -> control.buscarAlumnos(newValue));
        idvolver.setOnAction(e -> control.regresar());

        columnaEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCompleto()));
        columnaActa.setCellValueFactory(cellData -> cellData.getValue().tieneActaProperty());
        columnaCurp.setCellValueFactory(cellData -> cellData.getValue().tieneCurpProperty());
        columnaCertificado.setCellValueFactory(cellData -> cellData.getValue().tieneCertificadoProperty());
        columnaDomicilio.setCellValueFactory(cellData -> cellData.getValue().tieneDomicilioProperty());

        configurarCeldaConIcono(columnaActa, "Acta de Nacimiento");
        configurarCeldaConIcono(columnaCurp, "CURP");
        configurarCeldaConIcono(columnaCertificado, "Certificado Médico");
        configurarCeldaConIcono(columnaDomicilio, "Domicilio");

        configurarCeldaConBoton(columnaDescargar, "⬇️", "button-descargar");
        configurarCeldaConBoton(columnaNotificar, "Notificar", "button-notificar");
    }

    public void llenaTabla(List<AlumnoDocumentoRow> alumnos) {
        tablaEstudiantes.setItems(FXCollections.observableArrayList(alumnos));
    }

    private void configurarCeldaConIcono(TableColumn<AlumnoDocumentoRow, Boolean> columna, String tipoDocumento) {
        columna.setCellFactory(param -> new TableCell<>() {
            private final Label label = new Label();
            {
                label.setOnMouseClicked(event -> {
                    if (!isEmpty() && getItem()) {
                        AlumnoDocumentoRow alumno = getTableView().getItems().get(getIndex());
                        control.previsualizarDocumento(alumno, tipoDocumento);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    label.setText(item ? "📄" : "❌");
                    if (item) {
                        label.setCursor(Cursor.HAND);
                        label.setStyle("-fx-underline: true; -fx-text-fill: blue;");
                    } else {
                        label.setCursor(Cursor.DEFAULT);
                        label.setStyle("");
                    }
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
                btn.getStyleClass().add(estilo);
                btn.setOnAction(event -> {
                    AlumnoDocumentoRow alumno = getTableView().getItems().get(getIndex());
                    if ("Notificar".equals(textoBoton)) {
                        control.notificarPadre(alumno.getNombre(), alumno.getApellido());
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

    public void cierra() {
        if (stage != null) {
            stage.close();
        }
    }

    /**
     * MÉTODO AÑADIDO: Permite que el controlador obtenga la referencia al Stage.
     * Esto es necesario para que los diálogos (como FileChooser) sepan sobre
     * qué ventana principal mostrarse.
     * @return el Stage (ventana) actual.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Clase interna para representar los datos de una fila en la tabla.
     */
    public static class AlumnoDocumentoRow {
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty apellido;
        private final SimpleBooleanProperty tieneActa;
        private final SimpleBooleanProperty tieneCurp;
        private final SimpleBooleanProperty tieneCertificado;
        private final SimpleBooleanProperty tieneDomicilio;

        public AlumnoDocumentoRow(String nombre, String apellido, boolean acta, boolean curp, boolean cert, boolean dom) {
            this.nombre = new SimpleStringProperty(nombre);
            this.apellido = new SimpleStringProperty(apellido);
            this.tieneActa = new SimpleBooleanProperty(acta);
            this.tieneCurp = new SimpleBooleanProperty(curp);
            this.tieneCertificado = new SimpleBooleanProperty(cert);
            this.tieneDomicilio = new SimpleBooleanProperty(dom);
        }

        public SimpleStringProperty nombreProperty() { return nombre; }
        public SimpleStringProperty apellidoProperty() { return apellido; }
        public SimpleBooleanProperty tieneActaProperty() { return tieneActa; }
        public SimpleBooleanProperty tieneCurpProperty() { return tieneCurp; }
        public SimpleBooleanProperty tieneCertificadoProperty() { return tieneCertificado; }
        public SimpleBooleanProperty tieneDomicilioProperty() { return tieneDomicilio; }

        public String getNombreCompleto() { return nombre.get() + " " + apellido.get(); }
        public String getNombre() { return nombre.get(); }
        public String getApellido() { return apellido.get(); }
    }
}