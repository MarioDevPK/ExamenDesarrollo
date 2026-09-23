package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarrollo.entity.UnidadAprendizaje;
import java.util.List;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegateUnidadAprendizaje;

    public FacadeUnidadAprendizaje() {
        this.delegateUnidadAprendizaje = new DelegateUnidadAprendizaje();
    }

    public void guardarUnidad(UnidadAprendizaje unidad) {
        delegateUnidadAprendizaje.saveUnidad(unidad);
    }

    public List<UnidadAprendizaje> consultarUnidades() {
        return delegateUnidadAprendizaje.getUnidades();
    }

    public boolean actualizarUnidad(UnidadAprendizaje unidad) {
        return delegateUnidadAprendizaje.actualizarUnidad(unidad);
    }

    public boolean eliminarUnidad(int id) {
        return delegateUnidadAprendizaje.eliminarUnidad(id);
    }

    public boolean existeUnidad(String nombre) {
        return delegateUnidadAprendizaje.existeUnidad(nombre);
    }
}
