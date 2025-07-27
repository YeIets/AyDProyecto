package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import java.util.List;


/**
 *
 * Entidad de negocio Padre
 *
 */

@Entity
public class Padre {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idPadre;

    private String nombre;

    private String apellido;

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


    public long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(long idPadre) {
        this.idPadre = idPadre;
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

    public List<Alumno> getHijos() {
        return hijos;
    }

    public void setHijos(List<Alumno> hijos) {
        this.hijos = hijos;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
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
