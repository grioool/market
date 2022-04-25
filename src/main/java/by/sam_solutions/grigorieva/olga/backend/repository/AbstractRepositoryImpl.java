package by.sam_solutions.grigorieva.olga.backend.repository;

import by.sam_solutions.grigorieva.olga.backend.entity.AbstractEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class AbstractRepositoryImpl<Entity extends AbstractEntity> implements AbstractRepository<Entity> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<Entity> parametrizedType;

    @SuppressWarnings("unchecked")
    public AbstractRepositoryImpl() {
        this.parametrizedType = ((Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Entity create(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Entity update(Entity entity) {
        entityManager.merge(entity);
        return entity;
    }

    public void delete(int id) {
        entityManager.remove(getById(id));
    }

    public Entity getById(int id) {
        return entityManager.find(parametrizedType, id);
    }

    public List<Entity> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> cq = cb.createQuery(parametrizedType);
        Root<Entity> rootEntry = cq.from(parametrizedType);
        CriteriaQuery<Entity> all = cq.select(rootEntry);
        TypedQuery<Entity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
