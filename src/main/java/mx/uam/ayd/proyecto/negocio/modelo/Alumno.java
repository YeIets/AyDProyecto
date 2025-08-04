package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Entidad de negocio Alumno
 *
 */

@Entity
public class Alumno {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private long idAlumno;

    private String nombre;

    private String apellido;

    private int matricula;

    @OneToMany(
        targetEntity = Menu.class, 
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(
        targetEntity = Documento.class, 
        fetch = FetchType.EAGER, 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Documento> documentos = new ArrayList <>(5);

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idAlumno);
    }
    
    @Override
    public String toString() {
        return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre 
        + ", apellido=" + apellido + ", matricula=" + matricula 
        + ", menus=" + menus + ", documentos" + documentos + "]";
    }

}
