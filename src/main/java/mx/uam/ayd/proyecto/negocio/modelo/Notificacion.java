package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import java.time.LocalTime;


/**
 *
 * Entidad de negocio Alumno
 *
 */

@Entity
public class Notificacion {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private long idNotificacion;

    private String titulo;

    private LocalTime hora;

    private String mensaje;

    private String enlace;

    private String ventana;

    private int matricula;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Padre destinatario;

    public long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getVentana() {
        return ventana;
    }

    public void setVentana(String ventana) {
        this.ventana = ventana;
    }

    public Padre getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Padre destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Notificacion [idNotificacion=" + idNotificacion + ", titulo=" + titulo + 
        ", destinatario=" + destinatario + ", hora=" + hora + + ", mensaje" + mensaje +"]";
    }
}
