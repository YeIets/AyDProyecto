package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor; // Import para el constructor sin argumentos
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entidad de negocio Alumno
 */
@Data
@Entity
@NoArgsConstructor // Lombok crea el constructor vacío requerido por JPA
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumno;

    private String nombre;
    private String apellido;
    private String matricula;

    // ✅ RELACIÓN AÑADIDA: La contraparte de la relación. Muchos Alumnos tienen un Padre.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "padre_id") // Define la columna de llave foránea en la tabla Alumno
    private Padre padre;

    @OneToMany(
            targetEntity = Menu.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Menu> menus = new ArrayList<>(5);

    @OneToMany(
            targetEntity = Documento.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Documento> documentos = new ArrayList<>(5);


    public Alumno(String nombre, String apellido, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    public List<Documento> getDocumentosPorTipo(String tipo) {
        return this.documentos.stream()
                .filter(documento -> documento.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    public boolean agregarDocumento(Documento documento) {
        if (documento == null) {
            throw new IllegalArgumentException("Documento no puede ser nulo");
        }
        if (documentos.contains(documento)) {
            return false;
        }
        documento.setAlumno(this);
        return documentos.add(documento);
    }

    public boolean agregarMenu(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("Menu no puede ser nulo");
        }
        if (menus.contains(menu)) {
            return false;
        }
        return menus.add(menu);
    }

    @Override
    public String toString() {
        String padreInfo = (padre != null) ? "Padre: " + padre.getNombre() : "Padre: (sin asignar)";
        return "Alumno [id=" + idAlumno + ", nombre=" + nombre + ", " + padreInfo + "]";
    }
}