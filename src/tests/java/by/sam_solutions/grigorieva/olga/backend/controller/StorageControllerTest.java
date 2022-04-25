package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.dto.CountryDto;
import by.sam_solutions.grigorieva.olga.backend.dto.StorageDto;
import by.sam_solutions.grigorieva.olga.backend.dto.TownDto;
import by.sam_solutions.grigorieva.olga.backend.entity.country.CountryName;
import by.sam_solutions.grigorieva.olga.backend.entity.town.TownName;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StorageControllerTest extends TestCase {

//    @Autowired
//    private MockMvc mockMvc;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private static final List<StorageDto> storageList = new ArrayList<>();
//
//    @BeforeClass
//    public static void init() {
//
//        CountryDto country =  new CountryDto();
//        country.setCountryName(CountryName.BELARUS);
//
//        TownDto town = new TownDto();
//        town.setTownName(TownName.MINSK);
//
//        StorageDto storage =  new StorageDto();
//        storage.setCountry(country);
//        storage.setTown(town);
//
//        CountryDto country1 =  new CountryDto();
//        country.setCountryName(CountryName.UKRAINE);
//
//        TownDto town1 = new TownDto();
//        town.setTownName(TownName.KIEV);
//
//        StorageDto storage1 =  new StorageDto();
//        storage.setCountry(country1);
//        storage.setTown(town1);
//
//        CountryDto country2 =  new CountryDto();
//        country.setCountryName(CountryName.RUSSIA);
//
//        TownDto town2 = new TownDto();
//        town.setTownName(TownName.MOSCOW);
//
//        StorageDto storage2 =  new StorageDto();
//        storage.setCountry(country);
//        storage.setTown(town);
//
//        storageList.add(storage);
//        storageList.add(storage1);
//        storageList.add(storage2);
//    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_1_shouldFetchAllStorages() throws Exception {
//        List<LinkedHashMap<String, Object>> storages = objectMapper.readValue(this.mockMvc.perform(get("/storages"))
//                        .andExpect(status().isOk())
//                        .andReturn()
//                        .getResponse()
//                        .getContentAsString(),
//                List.class
//        );
//        Assertions.assertThat(storageList.stream().allMatch(storageDto ->
//                storages.stream().anyMatch(storage -> storage.get("id").equals(storageDto.getId()))
//        )).isEqualTo(true);
    }

}