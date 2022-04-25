package by.sam_solutions.grigorieva.olga.backend.repository.role;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {HibernateConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class RoleRepositoryTest extends TestCase {

    @Autowired
    private RoleRepository roleRepository;

    private static Role r;

    @Test
    @Rollback(value = false)
    public void test_1_createTest(){

        Role role = new Role();
        role.setRoleName("TEST");

        roleRepository.create(role);

        r = role;

        Assertions.assertThat(role.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getTest(){

        Role role = roleRepository.getById(r.getId());

        Assertions.assertThat(role.getId()).isEqualTo(r.getId());
    }

    @Test
    public void test_3_getAllTest(){

        List<Role> roles = roleRepository.getAll();

        Assertions.assertThat(roles.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_updateTest(){

        Role role = roleRepository.getById(r.getId());

        role.setRoleName("TEST");

        Role roleUpdated =  roleRepository.update(role);

        Assertions.assertThat(roleUpdated.getRoleName()).isEqualTo("TEST");

    }

    @Test
    @Rollback(value = false)
    public void test_5_deleteTest(){
        roleRepository.delete(r.getId());
        Assertions.assertThat(roleRepository.getById(r.getId())).isNull();
    }
}