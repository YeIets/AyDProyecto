package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.AdministrativoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Administrativo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio de negocio para la entidad Administrativo.
 */
@Service
public class ServicioAdministrativo {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    /**
     * Autentica a un administrador verificando sus credenciales.
     * @param correo El correo electrónico del administrador.
     * @param password La contraseña del administrador.
     * @return La entidad Administrativo si la autenticación es exitosa; de lo contrario, retorna null.
     */
    // CAMBIO: Se corrigió el nombre del método de "...vo" a "...dor" para ser consistente.
    public Administrativo verificarAdministradorRegistrado(String correo, String password) {
        Optional<Administrativo> adminOpt = administrativoRepository.findByCorreo(correo);

        if (adminOpt.isPresent()) {
            Administrativo admin = adminOpt.get();
            if (admin.getPassword().equals(password)) {
                return admin; // Credenciales válidas.
            }
        }

        return null; // Credenciales inválidas o el usuario no existe.
    }
}