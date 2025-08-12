package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DocumentoTest {

    private Documento documento;
    private LocalDate fecha = LocalDate.now();

    @BeforeEach
    void setUp() {
        documento = new Documento();
        documento.setIdDocumento(1L);
        documento.setNombre("Curp.pdf");
        documento.setTipo("CURP");
        documento.setDireccionArchivo("Descargas/Curp.pdf");
        documento.setFechaDeSubida(fecha);
    }

    @Test
    @DisplayName("ToString debería devolver la representación correcta en texto")
    void testToString() {
        String esperado = "Documento [idDocumento=1, Nombre=Curp.pdf, tipo=CURP, Direccion=Descargas/Curp.pdf, Fecha=" + fecha.toString() + "]";
        assertEquals(esperado, documento.toString());
    }

}
