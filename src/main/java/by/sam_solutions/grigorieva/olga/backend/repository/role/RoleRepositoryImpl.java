package by.sam_solutions.grigorieva.olga.backend.repository.role;

import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

    public Role findByName(String name) {
        return entityManager.createQuery(
                        "SELECT r from Role r WHERE r.roleName = :roleName", Role.class
                )
                .setParameter("roleName", name)
                .getSingleResult();
    }
}
