package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

class AlumnoTest {

    private Alumno alumno;
    private Menu menu = new Menu(1L, "Tacos", "Jamaica", "Limon", "Manzana", "Lunes");
    private Documento documento = new Documento(1L, "Curp.pdf", "CURP", "Descargar/Curp.pdf", LocalDate.now());

    @BeforeEach
    void setUp() {
        alumno = new Alumno();
        alumno.setIdAlumno(1L);
        alumno.setNombre("Juan");
        alumno.setApellido("Pérez");
        alumno.setMatricula("1234567890");
    }


    @Test
    @DisplayName("Deberia agregar un menu correctamente a la lista de menus del Alumno")
    void testAgregarMenuExitosamente() {
        //When
        boolean resultado = alumno.agregarMenu(menu);

        //Then
        assertTrue(resultado, "Deberia poder agregar un menu a la lista");
        assertTrue(alumno.getMenus().contains(menu), "El menu deberia estar en la lista");
    }

    @Test
    @DisplayName("No deberia poder agregar un menu duplicado")
    void testNoAgregarMenuDuplicado() {
        //Given
        alumno.agregarMenu(menu);

        //When
        boolean resultado = alumno.agregarMenu(menu);

        //Then
        assertFalse(resultado, "No deberia poder agregar un menu a la lista");
        assertEquals(1, alumno.getMenus().size(), "La lista deberia tener solo 1 menu");
    }

    @Test
    @DisplayName("Deberia lanzar una IllegalArgumentException cuando se trata de agregar un nulo")
    void testNoAgregarMenuNulo(){
        //When -> Then

        assertThrows(IllegalArgumentException.class,
            () -> alumno.agregarMenu(null),
            "Deberia lanzar una IllegalArgumentException");
    }

    @Test
    @DisplayName("Deberia agregar un documento correctamente a la lista de documentos del Alumno")
    void testAgregarDocumentoExitosamente() {
        //When
        boolean resultado = alumno.agregarDocumento(documento);

        //Then
        assertTrue(resultado, "Deberia poder agregar un documento a la lista");
        assertTrue(alumno.getDocumentos().contains(documento), "El documento deberia estar en la lista");
    }

    @Test
    @DisplayName("No deberia poder agregar un documento duplicado")
    void testNoAgregarDocumentoDuplicado() {
        //Given
        alumno.agregarDocumento(documento);

        //When
        boolean resultado = alumno.agregarDocumento(documento);

        //Then
        assertFalse(resultado, "No deberia poder agregar un documento a la lista");
        assertEquals(1, alumno.getDocumentos().size(), "La lista deberia tener solo 1 documento");
    }

    @Test
    @DisplayName("Deberia lanzar una IllegalArgumentException cuando se trata de agregar un nulo")
    void testNoAgregarDocumentoNulo(){
        //When -> Then

        assertThrows(IllegalArgumentException.class,
            () -> alumno.agregarDocumento(null),
            "Deberia lanzar una IllegalArgumentException");
    }
}
