package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Entidad de negocio Padre
 *
 */
@Data
@Entity
public class Padre {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private Long idPadre;

    private String nombre;

    private String apellido;

    private String password;

    private String correo;

    @OneToMany(
        targetEntity = Alumno.class, 
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Alumno> hijos = new ArrayList<>();

    @OneToMany(
        targetEntity = Notificacion.class, 
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Notificacion> notificaciones = new ArrayList <> ();

    @OneToMany(
        targetEntity = Pago.class,
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Pago> pagos = new ArrayList <> ();

    public Padre(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Padre other = (Padre) obj;
        return idPadre == other.idPadre;
    }
    
    @Override
    public int hashCode() {
        return (int) (31 * idPadre);
    }
    
    @Override
    public String toString() {
        return "Padre [idPadre=" + idPadre + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + "]";
    }

}
