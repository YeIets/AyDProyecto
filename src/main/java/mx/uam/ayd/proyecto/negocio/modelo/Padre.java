package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor; // Import para el constructor sin argumentos

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de negocio Padre
 */
@Data
@Entity
@NoArgsConstructor // Lombok crea el constructor vacío requerido por JPA
public class Padre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPadre;

    private String nombre;
    private String apellido;
    private String password;
    private String correo;

    // ✅ RELACIÓN CORREGIDA: Se añadió mappedBy para la correcta vinculación
    @OneToMany(
            mappedBy = "padre", // Indica que el campo "padre" en Alumno gestiona esta relación
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
    private List<Notificacion> notificaciones = new ArrayList<>();

    @OneToMany(
            targetEntity = Pago.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pago> pagos = new ArrayList<>();

    // ✅ CONSTRUCTOR MEJORADO: Para crear un padre con todos sus datos
    public Padre(String nombre, String apellido, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    // ✅ MÉTODO DE AYUDA AÑADIDO: Para sincronizar la relación en ambos extremos
    public void addHijo(Alumno hijo) {
        this.hijos.add(hijo);
        hijo.setPadre(this);
    }

    public void agregaNotificacion(Notificacion noti) {
        notificaciones.add(noti);
    }
}