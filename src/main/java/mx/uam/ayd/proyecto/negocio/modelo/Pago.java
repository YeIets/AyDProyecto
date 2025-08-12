package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


/**
 *
 * Entidad de negocio Pago
 *
 */
@Data
@Entity
public class Pago {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private Long idPago;

    private int matricula;

    private float monto;

    private String metodoDePago;

    private LocalDate fechaPago;

    private LocalDate fechaLimite;

    private String conceptoDePago;

    private String estado;

    private String diaMenu;

    private String servicios;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Padre titular;

    public Pago(){

    }

    //Crea un Pago  con atributos "ConceptoDePago", "Estado" y "MetodoDePago" ya definidos
    //Usado para crear pagos en caja
    public Pago(Padre titular, float monto, String diaMenu){
        this.titular = titular;
        this.monto = monto;
        this.diaMenu = diaMenu;
    }

    public Pago(Padre titular, float monto){
        this.titular = titular;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Pago [idPago=" + idPago + ", matricula=" + matricula
        + ", monto=" + monto + ", metodo=" + metodoDePago + ", fechaPago=" + fechaPago
        + ", fechaLimite=" + fechaLimite + ", concepto=" + conceptoDePago + ", estado" + estado +"]";
    }

}
