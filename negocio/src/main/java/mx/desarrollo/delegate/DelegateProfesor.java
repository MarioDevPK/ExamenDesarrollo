package mx.desarrollo.delegate;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.facade.FacadeProfesor;
import mx.desarrollo.integration.ServiceFacadeLocator;

public class DelegateProfesor {

    private final FacadeProfesor facadeProfesor;

    public DelegateProfesor() {
        this.facadeProfesor = ServiceFacadeLocator.getInstanceFacadeProfesor();
    }

    public boolean altaProfesor(Profesor profesor) {
        return facadeProfesor.altaProfesor(profesor);
    }

    public boolean bajaProfesor(int idProfesor) {
        return facadeProfesor.bajaProfesor(idProfesor);
    }
}