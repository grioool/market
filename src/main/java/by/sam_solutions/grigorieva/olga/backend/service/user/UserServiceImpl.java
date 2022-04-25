package by.sam_solutions.grigorieva.olga.backend.service.user;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.UserRegistrationDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.exception.AuthenticationException;
import by.sam_solutions.grigorieva.olga.backend.exception.EmailAlreadyExists;
import by.sam_solutions.grigorieva.olga.backend.exception.UsernameAlreadyExists;
import by.sam_solutions.grigorieva.olga.backend.repository.role.RoleRepository;
import by.sam_solutions.grigorieva.olga.backend.repository.user.UserRepository;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegistrationDto userDto) {
        if (getByUsername(userDto.getUsername()) != null) throw new UsernameAlreadyExists();
        if (getByEmail(userDto.getEmail()) != null) throw new EmailAlreadyExists();

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setNameCompany(userDto.getNameCompany());
        user.setWildBerriesKeys(userDto.getWbKey());

        user.setRoles(List.of(roleRepository.findByName("USER")));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setIsBlocked(false);
        user.setIsSubscribed(false);
        userRepository.create(user);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.create(user);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.create(role);
    }

    @Override
    public boolean addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        return user.getRoles().add(role);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user.not.found");
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws AuthenticationException {
        return userRepository.findByEmail(email);
    }

    @Override
    public TablePage<User> getUsersPerPage(int shift, int rowsPerPage) {
        List<User> users = userRepository.getAll();
        return TablePage.slice(users, shift, rowsPerPage);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateResetPasswordToken(String token, String email) throws AuthenticationException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.create(user);
        } else {
            throw new AuthenticationException("user.not.found");
        }
    }

    public void updateResetPassword(String password, String email) throws AuthenticationException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPassword(passwordEncoder.encode(password));
            userRepository.create(user);
        } else {
            throw new AuthenticationException("user.not.found");
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        user.setResetPasswordToken(null);
        user.setResetPassword(null);
        userRepository.update(user);
    }
}
