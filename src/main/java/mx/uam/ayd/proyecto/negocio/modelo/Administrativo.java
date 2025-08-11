package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de negocio para Administrativo.
 */
@Data
@Entity
@NoArgsConstructor // Requerido por JPA y facilitado por Lombok.
public class Administrativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrativo;

    private String nombre;
    private String apellido;
    private String password;
    private String correo;

    /**
     * Constructor para inicializar una entidad Administrativo con todos sus datos.
     * @param nombre El nombre del administrativo.
     * @param apellido El apellido del administrativo.
     * @param correo El correo electrónico para el inicio de sesión.
     * @param password La contraseña para el inicio de sesión.
     */
    public Administrativo(String nombre, String apellido, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }
}