package by.sam_solutions.grigorieva.olga.backend.repository.town;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.town.Town;
import by.sam_solutions.grigorieva.olga.backend.entity.town.TownName;
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
public class TownRepositoryTest extends TestCase {

    @Autowired
    private TownRepository townRepository;

    private static Town t;

    @Test
    @Rollback(value = false)
    public void test_1_createTest(){

        Town town = new Town();
        town.setTownName(TownName.MINSK);

        townRepository.create(town);

        t = town;

        Assertions.assertThat(town.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getTest(){

        Town town = townRepository.getById(t.getId());

        Assertions.assertThat(town.getId()).isEqualTo(t.getId());
    }

    @Test
    public void test_3_getAllTest(){

        List<Town> towns = townRepository.getAll();

        Assertions.assertThat(towns.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_updateTest(){

        Town town = townRepository.getById(t.getId());

        town.setTownName(TownName.KIEV);

        Town townUpdated =  townRepository.update(town);

        Assertions.assertThat(townUpdated.getTownName()).isEqualTo(TownName.KIEV);

    }

    @Test
    @Rollback(value = false)
    public void test_5_deleteTest(){
        townRepository.delete(t.getId());
        Assertions.assertThat(townRepository.getById(t.getId())).isNull();
    }

}