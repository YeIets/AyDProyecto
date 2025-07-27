package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 *
 * Entidad de negocio Administrativo
 *
 */

@Entity
public class Administrativo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idAdministrativo;

    private String nombre;

    private String apellido;

    private String correo;

    public long getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idAdministrativo);
    }
    
    @Override
    public String toString() {
        return "Administrativo [idAdministrativo=" + idAdministrativo
        + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + "]";
    }
}