package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de negocio para EncargadoCocina.
 */
@Data
@Entity
@NoArgsConstructor // Requerido por JPA y facilitado por Lombok.
public class EncargadoCocina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncargadoCocina;

    private String nombre;
    private String apellido;
    private String password;
    private String correo;

    @OneToMany(
            targetEntity = Menu.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "idEncargadoCocina")
    private List<Menu> menusSemanal = new ArrayList<>();

    /**
     * Constructor para inicializar una entidad EncargadoCocina con todos sus datos.
     * @param nombre El nombre del encargado.
     * @param apellido El apellido del encargado.
     * @param correo El correo electrónico para el inicio de sesión.
     * @param password La contraseña para el inicio de sesión.
     */
    public EncargadoCocina(String nombre, String apellido, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }
}