package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.CountryDto;
import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import org.springframework.core.convert.converter.Converter;

public class CountryToDtoConverter implements Converter<Country, CountryDto> {

    @Override
    public CountryDto convert(Country country) {
        CountryDto dto = new CountryDto();
        dto.setId(country.getId());
        dto.setCountryName(country.getCountryName());
        return dto;
    }
}
