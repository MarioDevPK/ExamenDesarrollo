package mx.desarrollo.persistence.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.AsignacionUnidad;
import mx.desarrollo.persistence.persistence.AbstractDAO;

import java.util.List;

public class AsignacionUnidadDAO extends AbstractDAO<AsignacionUnidad> {

    private final EntityManager entityManager;

    public AsignacionUnidadDAO(EntityManager em) {
        super(AsignacionUnidad.class);
        this.entityManager = em;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean insertarAsignacion(AsignacionUnidad asignacion) {
        try {
            save(asignacion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<AsignacionUnidad> getAsignaciones() {
        return findAll();
    }

    public boolean eliminarAsignacion(int id) {
        try {
            AsignacionUnidad asignacion = find(id).orElse(null);

            if (asignacion != null) {
                delete(asignacion);
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
