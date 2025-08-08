package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Entidad de negocio EncargadoCocina
 *
 */
@Data
@Entity
public class EncargadoCocina {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idEncargadoCocina;

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

    @Override
    public String toString() {
        return "EncargadoCocina [idEncargadoCocina=" + idEncargadoCocina
        + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo 
        + ", menus=" + menusSemanal + "]";
    }

}