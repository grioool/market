package by.sam_solutions.grigorieva.olga.backend.repository.user;

import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepository;

public interface UserRepository extends AbstractRepository<User> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByResetPasswordToken(String token);
}
