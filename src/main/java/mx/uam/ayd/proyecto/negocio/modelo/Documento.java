package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Documento {

    private String nombre;
    private String tipo;

    // CAMBIO: El campo 'direccionArchivo' se ha renombrado a 'ruta' para mayor claridad y consistencia.
    private String ruta;

    private LocalDate fechaDeSubida;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    public Documento() {

    }

    // CAMBIO: El constructor ahora utiliza 'ruta'.
    public Documento(String nombre, String tipo, String ruta, LocalDate fechaDeSubida) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ruta = ruta;
        this.fechaDeSubida = fechaDeSubida;
    }

    @Override
    public String toString() {
        // CAMBIO: Se actualiza el método toString para reflejar el nuevo nombre del campo.
        return "Documento [idDocumento=" + idDocumento + ", Nombre=" + nombre + ", tipo=" + tipo + ", Ruta="
                + ruta + ", Fecha=" + fechaDeSubida + "]";
    }
}