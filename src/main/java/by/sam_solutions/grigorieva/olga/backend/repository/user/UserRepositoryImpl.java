package by.sam_solutions.grigorieva.olga.backend.repository.user;

import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepository {

    public User findByUsername(String username) {
        try {
            return entityManager.createQuery(
                            "SELECT u from User u WHERE u.username = :username", User.class
                    )
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return entityManager.createQuery(
                            "SELECT u from User u WHERE u.email = :email", User.class
                    )
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

   public User findByResetPasswordToken(String resetPasswordToken) {
       try {
           return entityManager.createQuery(
                           "SELECT u from User u WHERE u.resetPasswordToken = :resetPasswordToken", User.class
                   )
                   .setParameter("resetPasswordToken", resetPasswordToken)
                   .getSingleResult();
       } catch (NoResultException e) {
           return null;
       }
   }
}
