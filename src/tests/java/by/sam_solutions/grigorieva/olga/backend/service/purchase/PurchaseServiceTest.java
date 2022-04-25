package by.sam_solutions.grigorieva.olga.backend.service.purchase;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.user.UserService;
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
public class PurchaseServiceTest extends TestCase {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    private final String username = "testName";
    private final String email = "test@gmail.com";
    private final String testToken = "testToken";

    private static Purchase p;

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
        userService.create(user);

        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(0);
        purchase.setLogistics(0.0);
        purchase.setDate(Timestamp.from(Instant.now()));
        purchase.setExtra(0.0);
        purchase.setAmount(0);
        purchase.setProductName("");
        purchase.setBatchPrice(0.0);
        purchase.setPriceForOne(0.0);
        purchase.setCostPrice(0.0);
        purchase.setUser(user);

        purchaseService.create(purchase);

        p=purchase;

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getTest(){

        Purchase purchase = purchaseService.getById(p.getId());

        Assertions.assertThat(purchase.getId()).isEqualTo(p.getId());
    }

    @Test
    public void test_3_getAllTest(){

        List<Purchase> purchases = purchaseService.getAll();

        Assertions.assertThat(purchases.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_updateTest(){

        Purchase purchase = purchaseService.getById(p.getId());

        purchase.setPurchasePrice(1);

        Purchase purchaseUpdated =  purchaseService.update(purchase);

        Assertions.assertThat(purchaseUpdated.getPurchasePrice()).isEqualTo(1);

    }

    @Test
    @Rollback(value = false)
    public void test_6_deleteTest(){
        purchaseService.delete(p.getId());
        User user = userService.getByUsername(username);
        userService.delete(user.getId());

        Assertions.assertThat(purchaseService.getById(p.getId())).isNull();
    }

    @Test
    public void test_5_getByUser() {
        User user = userService.getByUsername(username);

        List<Purchase> purchases = purchaseService.getByUser(user);

        Assertions.assertThat(purchases.size()).isGreaterThan(0);

    }

}