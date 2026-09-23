package mx.desarrollo.facade;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.dao.ProfesorDAO;
import mx.desarrollo.persistence.integration.ServiceLocator;

public class FacadeProfesor {

    private final ProfesorDAO profesorDAO;

    public FacadeProfesor() {
        this.profesorDAO = ServiceLocator.getInstanceProfesorDAO();
    }

    public boolean altaProfesor(Profesor profesor) {
        if (!validarDatos(profesor)) {
            return false;
        }
        profesorDAO.save(profesor);
        return true;
    }

    public boolean bajaProfesor(int idProfesor) {
        return profesorDAO.find(idProfesor)
                .map(profesor -> {
                    profesorDAO.delete(profesor);
                    return true;
                })
                .orElse(false);
    }

    private boolean validarDatos(Profesor profesor) {
        if (profesor == null) {
            return false;
        }
        if (profesor.getNombreProfesor() == null || profesor.getNombreProfesor().isBlank()) {
            return false;
        }
        if (profesor.getApellidoPaterno() == null || profesor.getApellidoPaterno().isBlank()) {
            return false;
        }
        if (profesor.getApellidoMaterno() == null || profesor.getApellidoMaterno().isBlank()) {
            return false;
        }
        if (profesor.getRfc() == null || profesor.getRfc().isBlank() || profesor.getRfc().length() > 13) {
            return false;
        }
        return true;
    }
}