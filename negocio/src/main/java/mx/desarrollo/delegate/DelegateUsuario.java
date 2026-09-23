package mx.desarrollo.delegate;

import mx.desarrollo.entity.Usuario;
import mx.desarrollo.persistence.integration.ServiceLocator;

public class DelegateUsuario {

    public Usuario login(String nombreUsuario, String contrasena) {
        return ServiceLocator.getInstanceUsuarioDAO()
                .find(nombreUsuario)
                .filter(u -> u.getContrasena().equals(contrasena))
                .orElse(null);
    }

    public void saveUsario(Usuario usuario) {
        ServiceLocator.getInstanceUsuarioDAO().save(usuario);
    }
}
