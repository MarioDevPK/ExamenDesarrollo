package mx.desarrollo.persistence.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.persistence.AbstractDAO;

import java.util.List;

public class ProfesorDAO extends AbstractDAO<Profesor> {
    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em) {
        super(Profesor.class);
        this.entityManager = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Profesor> findAllConAsignaciones() {
        return execute(em -> em.createQuery(
                "SELECT DISTINCT p FROM Profesor p " +
                        "LEFT JOIN FETCH p.asignacionUnidads a " +
                        "LEFT JOIN FETCH a.idUnidad " +
                        "ORDER BY p.nombreProfesor, p.apellidoPaterno, p.apellidoMaterno",
                Profesor.class
        ).getResultList());
    }
}
