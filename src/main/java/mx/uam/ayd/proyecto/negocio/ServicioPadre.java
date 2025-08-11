package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.PadreRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Padre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio relacionado con la entidad Padre.
 */
@Service
public class ServicioPadre {

    @Autowired
    private PadreRepository padreRepository;

    /**
     * Recupera todas las entidades Padre de la base de datos.
     * @return una lista de entidades Padre.
     */
    public List<Padre> recuperaPadres() {
        List<Padre> padres = new ArrayList<>();
        padreRepository.findAll().forEach(padres::add);
        return padres;
    }

    /**
     * Autentica a un padre verificando sus credenciales.
     * @param correo El correo electrónico del padre.
     * @param password La contraseña del padre.
     * @return La entidad Padre si la autenticación es exitosa; de lo contrario, retorna null.
     */
    public Padre verificarPadreRegistrado(String correo, String password) {
        return padreRepository.findByCorreoAndPassword(correo, password);
    }

    public Padre recuperaPadrePorCorreo(String correo) {
        return padreRepository.findByCorreo(correo);
    }
}