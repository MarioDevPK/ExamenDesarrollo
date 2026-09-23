package mx.desarrollo.persistence.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.persistence.AbstractDAO;
import java.util.List;

public class UnidadAprendizajeDAO extends AbstractDAO<UnidadAprendizaje> {

    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        super(UnidadAprendizaje.class);
        this.entityManager = em;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean insertUnidad(UnidadAprendizaje unidad) {
        try {
            save(unidad);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<UnidadAprendizaje> getUnidades() {
        return findAll();
    }

    public boolean actualizarUnidad(UnidadAprendizaje unidad) {
        try {
            update(unidad);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarUnidad(int id) {
        try {
            UnidadAprendizaje unidad = find(id).orElse(null);

            if (unidad != null) {
                delete(unidad);
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean existeUnidad(String nombre) {
        return !findByOneParameter(nombre, "nombre").isEmpty();
    }
}