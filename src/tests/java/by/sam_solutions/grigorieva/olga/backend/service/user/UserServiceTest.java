package by.sam_solutions.grigorieva.olga.backend.service.user;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.UserRegistrationDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {HibernateConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends TestCase {

    @Autowired
    private UserService userService;

    private final String username = "testName";
    private final String email = "test@gmail.com";
    private final String newEmail = "new@gmail.com";
    private final String testToken = "testToken";

    @Test
    public void test_1_register() {

        UserRegistrationDto user = new UserRegistrationDto();
        user.setPassword("");
        user.setUsername(username);
        user.setEmail(email);
        user.setNameCompany("");
        user.setWbKey("");

        userService.register(user);
    }

    @Test
    public void test_2_getByUsername() {

        User user = userService.getByUsername(username);

        Assertions.assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    public void test_3_getAllUsersTest(){

        List<User> users = userService.getAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);

    }

    @Test
    public void test_4_getUserTest(){

        User user = userService.getById(1);

        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    public void test_5_getByEmail() {

        User user = userService.getByEmail(email);

        Assertions.assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @Rollback(value = false)
    public void test_6_createUserTest(){

        User user = new User();
        user.setPassword("");
        user.setUsername(username + "1");
        user.setEmail(email + "1");
        user.setNameCompany("");
        user.setWildBerriesKeys("");

        user.setRoles(List.of());
        user.setPassword("");

        user.setIsBlocked(false);
        user.setIsSubscribed(false);
        user.setResetPasswordToken(testToken);

        userService.create(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    public void test_7_updateUserTest(){

        User user = userService.getByEmail(email);

        user.setEmail(newEmail);

        User userUpdated =  userService.update(user);

        Assertions.assertThat(userUpdated.getEmail()).isEqualTo(newEmail);

    }

    @Test
    public void test_8_getByResetPasswordToken() {

        User user = userService.getByResetPasswordToken(testToken);

        Assertions.assertThat(user.getResetPasswordToken()).isEqualTo(testToken);
    }

    @Test
    public void test_9_0_getUsersPerPage() {

        TablePage<User> users = userService.getUsersPerPage(1,1);

        Assertions.assertThat(users.getItems().size()).isGreaterThan(0);
    }

    @Test
    public void test_9_1_createUser() {

        User user = new User();
        user.setPassword("");
        user.setUsername(username + "2");
        user.setEmail(email + "2");
        user.setNameCompany("");
        user.setWildBerriesKeys("");

        user.setRoles(List.of());
        user.setPassword("");

        user.setIsBlocked(false);
        user.setIsSubscribed(false);
        user.setResetPasswordToken(testToken + "2");

        userService.createUser(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    public void test_9_2_updatePassword() {

        User user = userService.getByResetPasswordToken(testToken);

        user.setResetPasswordToken(testToken + "updated");

        User userUpdated =  userService.update(user);

        Assertions.assertThat(userUpdated.getResetPasswordToken()).isEqualTo(testToken + "updated");
    }


    @Test
    public void test_9_3_createRole() {

        Role role = new Role();
        role.setRoleName("test");

        userService.createRole(role);

        Assertions.assertThat(role.getId()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    public void test_9_4_deleteUserTest(){

        User user = userService.getByUsername(username);
        User user1 = userService.getByUsername(username + "1");
        User user2 = userService.getByUsername(username + "2");

        userService.delete(user.getId());
        userService.delete(user1.getId());
        userService.delete(user2.getId());

        User userCheck = null;

        Optional<User> optionalUser = Optional.ofNullable(userService.getByUsername(username));

        if(optionalUser.isPresent()){
            userCheck = optionalUser.get();
        }

        Assertions.assertThat(userCheck).isNull();
    }
}