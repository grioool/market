package by.sam_solutions.grigorieva.olga.backend.repository.role;

import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepository;

public interface RoleRepository extends AbstractRepository<Role> {

    Role findByName(String name);
}
