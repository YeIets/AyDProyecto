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
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
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
     * Inicializa la BD. Este método ahora está vacío porque los datos
     * ya existen en la base de datos persistente.
     */
    public void inicializaBD() {
        log.info("Verificando la inicialización de la base de datos. Si los datos ya existen, no se añadirán nuevos.");
        // Este método se deja vacío intencionalmente para no crear datos duplicados.
    }

    /*
     * ===================================================================
     * EJEMPLOS DE CÓDIGO PARA AÑADIR NUEVOS USUARIOS DE PRUEBA
     * ===================================================================
     *
     * Si en el futuro borras tu base de datos (el archivo datosDB.mv.db)
     * y necesitas volver a crear los datos de prueba, puedes llamar a este
     * método desde inicia() y quitar los comentarios.
     *

    @SuppressWarnings("unused")
    private void _ejemplosDeCreacionDeDatos() {

        // Ejemplo para crear un Administrador
        String correoAdmin = "Humberto@uam.com";
        if (administrativoRepository.findByCorreo(correoAdmin).isEmpty()) {
            log.info("Creando administrador de prueba: {}", correoAdmin);
            Administrativo admin = new Administrativo("Humberto", "Cervantes", correoAdmin, "Humberto123");
            administrativoRepository.save(admin);
        }

        // Ejemplo para crear un Encargado de Cocina
        String correoCocinero = "Alberto@uam.com";
        if (encargadoCocinaRepository.findByCorreo(correoCocinero).isEmpty()) {
            log.info("Creando encargado de cocina de prueba: {}", correoCocinero);
            EncargadoCocina encargado = new EncargadoCocina("Alberto", "Morales", correoCocinero, "Alberto123");
            encargadoCocinaRepository.save(encargado);
        }

        // Ejemplo para crear un Padre con Alumnos y Documentos
        String correoPadre = "Juan@uam.com";
        if (padreRepository.findByCorreo(correoPadre).isEmpty()) {
            log.info("Creando padre, alumnos y documentos de prueba para: {}", correoPadre);
            Padre padre = new Padre("Juan", "Pérez", correoPadre, "Juan123");

            Notificacion noti = new Notificacion(padre);
            padre.agregaNotificacion(noti);

            Alumno alumno1 = new Alumno("Sofia", "Pérez", "2193034567");
            Alumno alumno2 = new Alumno("Luis", "Pérez", "2193034588");

            Documento curpSofia = new Documento("CURP de Sofia", "CURP", "documentos_subidos/CURP_Sofia.pdf", LocalDate.now());
            Documento actaSofia = new Documento("Acta de Sofia", "Acta de Nacimiento", "documentos_subidos/Acta_Sofia.pdf", LocalDate.now());
            alumno1.agregarDocumento(curpSofia);
            alumno1.agregarDocumento(actaSofia);

            Documento actaLuis = new Documento("Acta de Luis", "Acta de Nacimiento", "documentos_subidos/Acta_Luis.pdf", LocalDate.now());
            alumno2.agregarDocumento(actaLuis);

            padre.addHijo(alumno1);
            padre.addHijo(alumno2);

            padreRepository.save(padre);
        }
    }
    */
}