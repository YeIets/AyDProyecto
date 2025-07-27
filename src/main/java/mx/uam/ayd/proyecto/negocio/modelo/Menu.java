package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import java.util.List;


/**
 *
 * Entidad de negocio Menu
 *
 */

@Entity
public class Menu {

    @Id // Esto le dice a Spring que este es el identificador
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Le dice a Spring que genere el id
    private long idMenu;

    private String comida;

    private String agua;

    private String gelatina;

    private String fruta;

    private String dia;

    public long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(long idMenu) {
        this.idMenu = idMenu;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getGelatina() {
        return gelatina;
    }

    public void setGelatina(String gelatina) {
        this.gelatina = gelatina;
    }

    public String getFruta() {
        return fruta;
    }

    public void setFruta(String fruta) {
        this.fruta = fruta;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Menu [idMenu=" + idMenu
        + ", comida=" + comida + ", agua=" + agua + ", gelatina=" + gelatina 
        + ", fruta=" + fruta + ", dia=" + dia +"]";
    }

}