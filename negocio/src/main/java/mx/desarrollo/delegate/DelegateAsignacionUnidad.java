package mx.desarrollo.delegate;

import mx.desarrollo.entity.AsignacionUnidad;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateAsignacionUnidad {

    public boolean insertarAsignacion(AsignacionUnidad asignacion) {
        return ServiceLocator.getInstanceAsignacionUnidadDAO()
                .insertarAsignacion(asignacion);
    }

    public List<AsignacionUnidad> getAsignaciones() {
        return ServiceLocator.getInstanceAsignacionUnidadDAO()
                .getAsignaciones();
    }

    public boolean eliminarAsignacion(int id) {
        return ServiceLocator.getInstanceAsignacionUnidadDAO()
                .eliminarAsignacion(id);
    }
}