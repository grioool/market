package by.sam_solutions.grigorieva.olga.backend.service.storage;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.entity.Storage;
import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import by.sam_solutions.grigorieva.olga.backend.entity.country.CountryName;
import by.sam_solutions.grigorieva.olga.backend.entity.town.Town;
import by.sam_solutions.grigorieva.olga.backend.entity.town.TownName;
import by.sam_solutions.grigorieva.olga.backend.repository.country.CountryRepository;
import by.sam_solutions.grigorieva.olga.backend.repository.town.TownRepository;
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
public class StorageServiceTest extends TestCase {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TownRepository townRepository;

    private static Storage s;

    @Test
    @Rollback(value = false)
    public void test_1_createTest(){

        Country country = new Country();
        country.setCountryName(CountryName.BELARUS);

        countryRepository.create(country);

        Town town = new Town();
        town.setTownName(TownName.MINSK);

        townRepository.create(town);

        Storage storage = new Storage();
        storage.setTown(town);
        storage.setCountry(country);

        storageService.create(storage);

        s = storage;

        Assertions.assertThat(storage.getId()).isGreaterThan(0);
    }

    @Test
    public void test_2_getTest(){

        Storage storage = storageService.getById(s.getId());

        Assertions.assertThat(storage.getId()).isEqualTo(s.getId());
    }

    @Test
    public void test_3_getAllTest(){

        List<Storage> storages = storageService.getAll();

        Assertions.assertThat(storages.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void test_4_updateTest(){

        Storage storage = storageService.getById(s.getId());

        Country country = new Country();
        country.setCountryName(CountryName.RUSSIA);

        countryRepository.create(country);

        storage.setCountry(country);

        Storage storageUpdated =  storageService.update(storage);

        Assertions.assertThat(storageUpdated.getCountry().getCountryName()).isEqualTo(CountryName.RUSSIA);

    }

    @Test
    @Rollback(value = false)
    public void test_5_deleteTest(){
        storageService.delete(s.getId());
        Assertions.assertThat(storageService.getById(s.getId())).isNull();
    }

}