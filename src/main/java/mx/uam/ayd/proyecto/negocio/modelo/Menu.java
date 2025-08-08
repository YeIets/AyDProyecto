package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

/**
 *
 * Entidad de negocio Menu
 *
 */
@Data
@Entity
public class Menu {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private Long idMenu;

    private String comida;

    private String agua;

    private String gelatina;

    private String fruta;

    private String dia;

    public Menu(Long idMenu, String comida, String agua, String gelatina, String fruta, String dia) {
        this.idMenu = idMenu;
        this.comida = comida;
        this.agua = agua;
        this.gelatina = gelatina;
        this.fruta = fruta;
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Menu [idMenu=" + idMenu
        + ", comida=" + comida + ", agua=" + agua + ", gelatina=" + gelatina 
        + ", fruta=" + fruta + ", dia=" + dia +"]";
    }

}