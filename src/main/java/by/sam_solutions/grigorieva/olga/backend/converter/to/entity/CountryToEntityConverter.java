package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.CountryDto;
import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import org.springframework.core.convert.converter.Converter;

public class CountryToEntityConverter implements Converter<CountryDto, Country> {

    @Override
    public Country convert(CountryDto countryDto) {
        Country country = new Country();
        country.setId(countryDto.getId());
        country.setCountryName(countryDto.getCountryName());
        return country;
    }
}
