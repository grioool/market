package by.sam_solutions.grigorieva.olga.backend.repository.country;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import by.sam_solutions.grigorieva.olga.backend.entity.country.CountryName;
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
public class CountryRepositoryTest extends TestCase {

    @Autowired
    private CountryRepository countryRepository;

    private static Country c;

    @Test
    @Rollback(value = false)
    public void test_1_createTest(){

        Country country = new Country();
        country.setCountryName(CountryName.BELARUS);

        countryRepository.create(country);
        c = country;

        Assertions.assertThat(country.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getTest(){

        Country country = countryRepository.getById(c.getId());

        Assertions.assertThat(country.getId()).isEqualTo(c.getId());
    }

    @Test
    public void test_3_getAllTest(){

        List<Country> countries = countryRepository.getAll();

        Assertions.assertThat(countries.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_updateTest(){

        Country country = countryRepository.getById(c.getId());

        country.setCountryName(CountryName.RUSSIA);

        Country countryUpdated =  countryRepository.update(country);

        Assertions.assertThat(countryUpdated.getCountryName()).isEqualTo(CountryName.RUSSIA);

    }

    @Test
    @Rollback(value = false)
    public void test_5_deleteTest(){
        countryRepository.delete(c.getId());
        Assertions.assertThat(countryRepository.getById(c.getId())).isNull();
    }

}