package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacionUnidad;
import mx.desarrollo.entity.AsignacionUnidad;

import java.util.List;

public class FacadeAsignacionUnidad {

    private final DelegateAsignacionUnidad delegateAsignacionUnidad;

    public FacadeAsignacionUnidad() {
        this.delegateAsignacionUnidad = new DelegateAsignacionUnidad();
    }

    public boolean insertarAsignacion(AsignacionUnidad asignacion) {
        return delegateAsignacionUnidad.insertarAsignacion(asignacion);
    }

    public List<AsignacionUnidad> consultarAsignaciones() {
        return delegateAsignacionUnidad.getAsignaciones();
    }

    public boolean eliminarAsignacion(int id) {
        return delegateAsignacionUnidad.eliminarAsignacion(id);
    }
}
