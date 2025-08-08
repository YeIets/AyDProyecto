package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Entidad de negocio Alumno
 *
 */
@Data
@Entity
public class Alumno {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private Long idAlumno;

    private String nombre;

    private String apellido;

    private String matricula;

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
    private List<Documento> documentos = new ArrayList <>(5);

    public boolean agregarDocumento(Documento documento){

        if (documento == null){
            throw new IllegalArgumentException("Documento no puede ser nulo");
        }

        //Checar si el menu esta en la lista de menu ya que no pueden haber duplicados
        if (documentos.contains(documento)) {
            return false;
        }

        return documentos.add(documento);
    }

    public boolean agregarMenu(Menu menu){

        if (menu == null){
            throw new IllegalArgumentException("Menu no puede ser nulo");
        }

        //Checar si el menu esta en la lista de menu ya que no pueden haber duplicados
        if (menus.contains(menu)) {
            return false;
        }

       return menus.add(menu);

   }
   
    @Override
    public String toString() {
        return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre 
        + ", apellido=" + apellido + ", matricula=" + matricula 
        + ", menus=" + menus.size() + ", documentos" + documentos.size() + "]";
    }

}
