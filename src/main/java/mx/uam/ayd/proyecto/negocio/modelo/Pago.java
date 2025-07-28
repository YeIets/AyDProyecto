package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * Entidad de negocio Pago
 *
 */

@Entity
public class Pago {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private long idPago;

    private int matriculaAlumno;

    private float monto;

    private String metodoDePago;

    private LocalDate fechaPago;

    private LocalDate fechaLimite;

    private String conceptoDePago;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Padre titular;

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public String getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(String matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getConceptoDePago() {
        return conceptoDePago;
    }

    public void setConceptoDePago(String conceptoDePago) {
        this.conceptoDePago = conceptoDePago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Padre getTitular() {
        return titular;
    }

    public void setTitular(Padre titular) {
        this.titular = titular;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idPago);
    }

    @Override
    public String toString() {
        return "Pago [idPago=" + idPago + ", matricula=" + matriculaAlumno
        + ", monto=" + monto + ", metodo=" + metodoDePago + ", fechaPago=" + fechaPago
        + ", fechaLimite=" + fechaLimite + ", concepto=" + conceptoDePago + ", estado" + estado +"]";
    }
}
