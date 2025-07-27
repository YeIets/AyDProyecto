package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;


/**
 *
 * Entidad de negocio Documento
 *
 */

@Entity
public class Documento {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private long idDocumento;

    private String tipo;

    private String direccionArchivo;

    private LocalDate fechaDeSubida;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    public long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }

    public LocalDate getFechaDeSubida() {
        return fechaDeSubida;
    }

    public void setFechaDeSubida(LocalDate fechaDeSubida) {
        this.fechaDeSubida = fechaDeSubida;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idDocumento);
    }
    
    @Override
    public String toString() {
        return "Documento [idDocumento=" + idDocumento + ", tipo=" + tipo 
        + ", direccion=" + direccionArchivo + ", fecha=" + fechaDeSubida + "]";
    }


}