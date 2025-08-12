package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToMany(
            mappedBy = "padre",
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


    public Padre(String nombre, String apellido, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    public void addHijo(Alumno hijo) {
        this.hijos.add(hijo);
        hijo.setPadre(this);
    }

    public void agregaNotificacion(Notificacion notificacion) {
        notificaciones.add(notificacion);
        notificacion.setDestinatario(this);
    }

    @Override
    public String toString() {
        String notificacionesInfo = (notificaciones != null) ? "Numero de Notificaciones: " + notificaciones.size() : "Numero de Notificaciones: 0";
        return "Padre [id=" + idPadre + ", nombre=" + nombre + ", " + notificacionesInfo + "]";
    }

    public void agregarPago(Pago pago) {
        if (pagos == null) {
            pagos = new ArrayList<>();
        }
        pago.setTitular(this);
        pagos.add(pago);
    }

    // ✅ NUEVO MÉTODO: Añadido para obtener un alumno para los flujos de pago.
    /**
     * Devuelve el primer alumno de la lista de hijos.
     * Este método sirve como puente para los flujos que operan con un solo alumno.
     *
     * @return El primer Alumno en la lista, o null si la lista está vacía.
     */
    public Alumno getAlumno() {
        if (this.hijos != null && !this.hijos.isEmpty()) {
            return this.hijos.get(0); // Devuelve el primer hijo
        }
        return null; // No hay hijos para devolver
    }
}