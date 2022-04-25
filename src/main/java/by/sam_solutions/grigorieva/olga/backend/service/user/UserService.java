package by.sam_solutions.grigorieva.olga.backend.service.user;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.UserRegistrationDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractService;

public interface UserService extends AbstractService<User> {

    void register(UserRegistrationDto userDto);

    User getByUsername(String username);

    Role createRole(Role role);

    boolean addRoleToUser(String username, String roleName);

    User createUser(User user);

    User getByEmail(String email);

    TablePage<User> getUsersPerPage(int shift, int rowsPerPage);

    void updateResetPasswordToken(String token, String email);

    void updateResetPassword(String password, String email);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

}
