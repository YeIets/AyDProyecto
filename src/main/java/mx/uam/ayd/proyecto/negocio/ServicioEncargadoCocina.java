package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.EncargadoCocinaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.EncargadoCocina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Servicio de negocio para la entidad EncargadoCocina.
 */
@Service
public class ServicioEncargadoCocina {

    @Autowired
    private EncargadoCocinaRepository encargadoCocinaRepository;

    /**
     * Valida si el día actual es miércoles.
     * @return true si es miércoles, false en caso contrario.
     */
    public boolean validaMiercoles() {
        return LocalDateTime.now().getDayOfWeek() == DayOfWeek.WEDNESDAY;
    }

    /**
     * Autentica a un encargado de cocina verificando sus credenciales.
     * @param correo El correo electrónico del encargado.
     * @param password La contraseña del encargado.
     * @return La entidad EncargadoCocina si la autenticación es exitosa; de lo contrario, retorna null.
     */
    public EncargadoCocina verificarEncargadoDeCocinaRegistrado(String correo, String password) {
        Optional<EncargadoCocina> encargadoOpt = encargadoCocinaRepository.findByCorreo(correo);

        if (encargadoOpt.isPresent()) {
            EncargadoCocina encargado = encargadoOpt.get();
            if (encargado.getPassword().equals(password)) {
                return encargado; // Credenciales válidas.
            }
        }

        return null; // Credenciales inválidas o el usuario no existe.
    }
}