package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data; // <-- Import para @Data
import java.time.LocalDate;

@Data // <-- ESTA ANOTACIÓN SOLUCIONA LOS GETTERS Y SETTERS
@Entity
public class Documento {

    private String nombre;
    private String tipo;
    private String direccionArchivo;
    private LocalDate fechaDeSubida;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;
    public Documento(){

    }

    public Documento(String nombre, String tipo, String direccionArchivo, LocalDate fechaDeSubida) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.direccionArchivo = direccionArchivo;
        this.fechaDeSubida = fechaDeSubida;
    }


    @Override
    public String toString() {
        return "Documento [idDocumento=" + idDocumento + ", Nombre=" + nombre + ", tipo=" + tipo + ", Direccion="
         + direccionArchivo + ", Fecha=" + fechaDeSubida + "]";
    }

}