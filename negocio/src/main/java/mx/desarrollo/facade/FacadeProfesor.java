package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }

    public boolean altaProfesor(Profesor profesor) {
        return delegateProfesor.altaProfesor(profesor);
    }

    public boolean bajaProfesor(int idProfesor) {
        return delegateProfesor.bajaProfesor(idProfesor);
    }

    public List<Profesor> consultarProfesoresConAsignaciones() {
        return delegateProfesor.obtenerProfesoresConAsignaciones();
    }
}
