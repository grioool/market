package by.sam_solutions.grigorieva.olga.backend.service;

import by.sam_solutions.grigorieva.olga.backend.entity.AbstractEntity;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractServiceImpl<Entity extends AbstractEntity> implements AbstractService<Entity> {

    @Autowired
    private AbstractRepository<Entity> abstractRepository;

    @Transactional
    public Entity create(Entity entity) {
        return abstractRepository.create(entity);
    }

    @Transactional
    public Entity update(Entity entity) {
        return abstractRepository.update(entity);
    }

    @Transactional
    public List<Entity> getAll() {
        return abstractRepository.getAll();
    }

    @Transactional
    public Entity getById(int id) {
        return abstractRepository.getById(id);
    }

    @Transactional
    public void delete(int id) {
        abstractRepository.delete(id);
    }
}
