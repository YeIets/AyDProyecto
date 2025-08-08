package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.Data;

/**
 *
 * Entidad de negocio Notificacion
 *
 */
@Data
@Entity
public class Notificacion {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private Long idNotificacion;

    private String titulo;

    private LocalTime hora;

    private String mensaje;

    private String enlace;

    private String ventana;

    private int matricula;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Padre destinatario;

    @Override
    public String toString() {
        return "Notificacion [idNotificacion=" + idNotificacion + ", titulo=" + titulo 
        + ", destinatario=" + destinatario + ", hora=" + hora + ", mensaje" + mensaje +"]";
    }
}
