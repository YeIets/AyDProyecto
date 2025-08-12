package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DocumentoTest {

    private Documento documento;
    private final LocalDate fecha = LocalDate.now();

    @BeforeEach
    void setUp() {
        documento = new Documento();
        documento.setIdDocumento(1L);
        documento.setNombre("Curp.pdf");
        documento.setTipo("CURP");

        // CAMBIO: Se usa el nuevo método setRuta() en lugar de setDireccionArchivo()
        documento.setRuta("Descargas/Curp.pdf");

        documento.setFechaDeSubida(fecha);
    }

    @Test
    @DisplayName("ToString debería devolver la representación correcta en texto")
    void testToString() {
        // CAMBIO: El texto esperado ahora coincide con el formato generado por @Data de Lombok,
        // usando el nuevo nombre de campo 'ruta' y los demás campos de la clase.
        String esperado = "Documento(idDocumento=1, nombre=Curp.pdf, tipo=CURP, ruta=Descargas/Curp.pdf, fechaDeSubida=" + fecha + ", alumno=null)";

        assertEquals(esperado, documento.toString());
    }
}