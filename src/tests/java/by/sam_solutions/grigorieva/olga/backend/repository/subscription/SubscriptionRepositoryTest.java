package by.sam_solutions.grigorieva.olga.backend.repository.subscription;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.Subscription;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.user.UserRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {HibernateConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class SubscriptionRepositoryTest extends TestCase {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    private final String username = "testName";
    private final String email = "test@gmail.com";
    private final String testToken = "testToken";

    private static Subscription s;

    @Test
    @Rollback(value = false)
    public void test_1_createTest(){

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

        Subscription subscription = new Subscription();
        subscription.setDateEnd(Timestamp.from(Instant.now()));
        subscription.setDateStart(Timestamp.from(Instant.now()));
        subscription.setPeriod(1);
        subscription.setUser(user);

        subscriptionRepository.create(subscription);

        s = subscription;

        Assertions.assertThat(subscription.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getTest(){

        Subscription subscription = subscriptionRepository.getById(s.getId());

        Assertions.assertThat(subscription.getId()).isEqualTo(s.getId());
    }

    @Test
    public void test_3_getUsersTest(){

        List<Subscription> subscriptions = subscriptionRepository.getAll();

        Assertions.assertThat(subscriptions.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_updateTest(){

        Subscription subscription = subscriptionRepository.getById(s.getId());

        subscription.setPeriod(2);

        Subscription subscriptionUpdated =  subscriptionRepository.update(subscription);

        Assertions.assertThat(subscriptionUpdated.getPeriod()).isEqualTo(2);

    }

    @Test
    @Rollback(value = false)
    public void test_5_deleteTest(){
        subscriptionRepository.delete(s.getId());
        User user = userRepository.findByUsername(username);
        userRepository.delete(user.getId());
        Assertions.assertThat(subscriptionRepository.getById(s.getId())).isNull();
    }

}