package by.sam_solutions.grigorieva.olga.backend.repository;

import java.util.List;

public interface AbstractRepository<Entity> {

    Entity create(Entity entity);

    Entity update(Entity entity);

    void delete(int id);

    Entity getById(int id);

    List<Entity> getAll();
}
