package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Entidad de negocio EncargadoCocina
 *
 */

@Entity
public class EncargadoCocina {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEncargadoCocina;

    private String nombre;

    private String apellido;

    private String correo;
   
    @OneToMany(
        targetEntity = Menu.class, 
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "idEncargadoCocina") // Llave Foranea de Menu
    private List<Menu> menusSemanal = new ArrayList<>();

    public long getIdEncargadoCocina() {

        return idEncargadoCocina;
    }

    public void setIdEncargadoCocina(long idEncargadoCocina) {
        this.idEncargadoCocina = idEncargadoCocina;
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

    public List<Menu> getMenusSemanal() {
        return menusSemanal;
    }

    public void setMenusSemanal(List<Menu> menusSemanal) {
        this.menusSemanal = menusSemanal;
    }

    @Override
    public String toString() {
        return "EncargadoCocina [idEncargadoCocina=" + idEncargadoCocina
        + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo 
        + ", menus=" + menusSemanal + "]";
    }

}