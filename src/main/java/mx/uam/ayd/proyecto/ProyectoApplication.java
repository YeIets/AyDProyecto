package mx.uam.ayd.proyecto;

import mx.uam.ayd.proyecto.datos.AdministrativoRepository;
import mx.uam.ayd.proyecto.datos.EncargadoCocinaRepository;
import mx.uam.ayd.proyecto.datos.AlumnoRepository;
import mx.uam.ayd.proyecto.datos.PadreRepository;
import mx.uam.ayd.proyecto.negocio.modelo.*;
import mx.uam.ayd.proyecto.presentacion.loginPrincipal.ControlLoginPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.time.LocalDate;

@SpringBootApplication
public class ProyectoApplication {

    private final ControlLoginPrincipal controlLoginPrincipal;
    private final PadreRepository padreRepository;
    private final AdministrativoRepository administrativoRepository;
    private final EncargadoCocinaRepository encargadoCocinaRepository;
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public ProyectoApplication(ControlLoginPrincipal controlLoginPrincipal, PadreRepository padreRepository, AdministrativoRepository administrativoRepository, EncargadoCocinaRepository encargadoCocinaRepository, AlumnoRepository alumnoRepository) {
        this.controlLoginPrincipal = controlLoginPrincipal;
        this.padreRepository = padreRepository;
        this.administrativoRepository = administrativoRepository;
        this.encargadoCocinaRepository = encargadoCocinaRepository;
        this.alumnoRepository = alumnoRepository;
    }

    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }

    public static class JavaFXApplication extends Application {
        private static ConfigurableApplicationContext applicationContext;

        @Override
        public void init() throws Exception {
            SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);
            builder.headless(false);
            applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
        }

        @Override
        public void start(Stage primaryStage) {
            Platform.runLater(() -> {
                applicationContext.getBean(ProyectoApplication.class).inicia();
            });
        }

        @Override
        public void stop() throws Exception {
            applicationContext.close();
            Platform.exit();
        }
    }

    public void inicia() {
        inicializaBD();
        Platform.runLater(() -> {
            controlLoginPrincipal.inicia();
        });
    }

    /**
     * Inicializa la BD con los datos de ejemplo.
     */
    public void inicializaBD() {
        // --- Creación de otros usuarios ---
        Administrativo admin = new Administrativo("Humberto", "Cervantes", "Humberto@uam.com", "Humberto123");
        EncargadoCocina encargado = new EncargadoCocina("Alberto", "Morales", "Alberto@uam.com", "Alberto123");
        administrativoRepository.save(admin);
        encargadoCocinaRepository.save(encargado);

        // --- LÓGICA PARA CREAR Y VINCULAR PADRE, ALUMNOS Y DOCUMENTOS ---
        Padre padre = new Padre("Juan", "Pérez", "Juan@uam.com", "Juan123");
        Notificacion noti = new Notificacion(padre);
        padre.agregaNotificacion(noti);

        Alumno alumno1 = new Alumno("Sofia", "Pérez", "2193034567");
        Alumno alumno2 = new Alumno("Luis", "Pérez", "2193034588");

        // --- Documentos para Alumno 1 (Sofía) ---
        Documento curpSofia = new Documento("CURP de Sofia", "CURP", "documentos_subidos/CURP_Sofia.pdf", LocalDate.now());
        Documento actaSofia = new Documento("Acta de Sofia", "Acta de Nacimiento", "documentos_subidos/Acta_Sofia.pdf", LocalDate.now());
        alumno1.agregarDocumento(curpSofia);
        alumno1.agregarDocumento(actaSofia);

        // --- Documentos para Alumno 2 (Luis) ---
        Documento actaLuis = new Documento("Acta de Luis", "Acta de Nacimiento", "documentos_subidos/Acta_Luis.pdf", LocalDate.now());
        alumno2.agregarDocumento(actaLuis);

        // Se vinculan los alumnos (ya con sus documentos) al padre
        padre.addHijo(alumno1);
        padre.addHijo(alumno2);

        // Se guarda el padre, y en cascada se guardan sus hijos y los documentos de sus hijos.
        padreRepository.save(padre);
    }
}