package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


/**
 *
 * Entidad de negocio Administrativo
 *
 */
@Data
@Entity
public class Administrativo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAdministrativo;

    private String nombre;

    private String apellido;

    private String correo;
    
    @Override
    public String toString() {
        return "Administrativo [idAdministrativo=" + idAdministrativo
        + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + "]";
    }
}