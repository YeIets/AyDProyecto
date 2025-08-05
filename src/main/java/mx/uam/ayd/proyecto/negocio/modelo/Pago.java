package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;


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

    private String diaMenu;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Padre titular;


    //Crea un Pago  con atributos "ConceptoDePago", "Estado" y "MetodoDePago" ya definidos
    //Usado para crear pagos en caja
    public Pago(Padre titular, float monto, String diaMenu){

        this.titular = titular;
        this.monto = monto;
        this.metodoDePago = "Caja";
        this.conceptoDePago = "Menu Semanal";
        this.estado = "Pendiente";
        this.diaMenu = diaMenu;

    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public int getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(int matriculaAlumno) {
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

    public String getDiaMenu() {
        return diaMenu;
    }

    public void setDiaMenu(String diaMenu) {
        this.diaMenu = diaMenu;
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
