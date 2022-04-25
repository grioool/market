package by.sam_solutions.grigorieva.olga.backend.repository.user;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {HibernateConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final String username = "testName";
    private final String email = "test@gmail.com";
    private final String testToken = "testToken";

    @Test
    @Rollback(value = false)
    public void test_1_createUserTest(){

        User user = new User();
        user.setPassword("");
        user.setUsername(username);
        user.setEmail(email);
        user.setNameCompany("");
        user.setWildBerriesKeys("");

        user.setRoles(List.of());
        user.setPassword("");

        user.setIsBlocked(false);
        user.setIsSubscribed(false);
        user.setResetPasswordToken(testToken);

        userRepository.create(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getUserTest(){

        User user = userRepository.getById(1);

        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    public void test_3_getAllUsersTest(){

        List<User> users = userRepository.getAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_findByUsernameTest() {

        User user = userRepository.findByUsername(username);

        Assertions.assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    @Rollback(value = false)
    public void test_5_findByEmailTest() {

        User user = userRepository.findByEmail(email);

        Assertions.assertThat(user.getEmail()).isEqualTo(email);

    }

    @Test
    @Rollback(value = false)
    public void test_6_updateUserTest(){

        User user = userRepository.findByResetPasswordToken(testToken);

        user.setEmail("new@gmail.com");

        User userUpdated =  userRepository.update(user);

        Assertions.assertThat(userUpdated.getEmail()).isEqualTo("new@gmail.com");

    }

    @Test
    @Rollback(value = false)
    public void test_7_findByResetPasswordTokenTest() {

        User user = userRepository.findByResetPasswordToken(testToken);

        Assertions.assertThat(user.getResetPasswordToken()).isEqualTo(testToken);
    }

    @Test
    @Rollback(value = false)
    public void test_8_deleteUserTest(){

        User user = userRepository.findByUsername(username);

        userRepository.delete(user.getId());

        User user1 = null;

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));

        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
        }

        Assertions.assertThat(user1).isNull();
    }
}