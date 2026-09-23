package mx.desarrollo.delegate;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateProfesor {

    public boolean altaProfesor(Profesor profesor) {
        if (!validarDatos(profesor)) {
            return false;
        }
        ServiceLocator.getInstanceProfesorDAO().save(profesor);
        return true;
    }

    public boolean bajaProfesor(int idProfesor) {
        return ServiceLocator.getInstanceProfesorDAO().find(idProfesor)
                .map(profesor -> {
                    ServiceLocator.getInstanceProfesorDAO().delete(profesor);
                    return true;
                })
                .orElse(false);
    }

    public List<Profesor> obtenerProfesoresConAsignaciones() {
        return ServiceLocator.getInstanceProfesorDAO().findAllConAsignaciones();
    }

    private boolean validarDatos(Profesor profesor) {
        if (profesor == null) return false;
        if (profesor.getNombreProfesor() == null || profesor.getNombreProfesor().isBlank()) return false;
        if (profesor.getApellidoPaterno() == null || profesor.getApellidoPaterno().isBlank()) return false;
        if (profesor.getApellidoMaterno() == null || profesor.getApellidoMaterno().isBlank()) return false;
        if (profesor.getRfc() == null || profesor.getRfc().isBlank() || profesor.getRfc().length() > 13) return false;
        return true;
    }
}
