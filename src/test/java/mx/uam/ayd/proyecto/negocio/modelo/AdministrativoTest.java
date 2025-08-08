package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AdministrativoTest {

    private Administrativo administrativo;

    @BeforeEach
    void setUp() {
        administrativo = new Administrativo();
        administrativo.setIdAdministrativo(1L);
        administrativo.setNombre("Juan");
        administrativo.setApellido("Pérez");
        administrativo.setCorreo("juan.perez@example.com");
    }

    @Test
    @DisplayName("ToString debería devolver la representación correcta en texto")
    void testToString() {
        String esperado = "Administrativo [idAdministrativo=1, nombre=Juan, apellido=Pérez, correo=juan.perez@example.com]";
        assertEquals(esperado, administrativo.toString());
    }
}
