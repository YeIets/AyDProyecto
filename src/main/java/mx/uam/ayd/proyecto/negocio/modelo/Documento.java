package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // <-- Import para @Data
import java.time.LocalDate;

@Data // <-- ESTA ANOTACIÓN SOLUCIONA LOS GETTERS Y SETTERS
@Entity
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private String direccionArchivo;
    private LocalDate fechaDeSubida;
}