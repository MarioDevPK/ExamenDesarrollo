package mx.desarrollo.delegate;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.integration.ServiceLocator;
import java.util.List;

public class DelegateUnidadAprendizaje {

    public void saveUnidad(UnidadAprendizaje unidad) {
        ServiceLocator.getInstanceUnidadAprendizajeDAO().save(unidad);
    }

    public List<UnidadAprendizaje> getUnidades() {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().getUnidades();
    }

    public boolean actualizarUnidad(UnidadAprendizaje unidad) {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().actualizarUnidad(unidad);
    }

    public boolean eliminarUnidad(int id) {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().eliminarUnidad(id);
    }

    public boolean existeUnidad(String nombre) {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().existeUnidad(nombre);
    }


}

