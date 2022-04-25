package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.TownDto;
import by.sam_solutions.grigorieva.olga.backend.entity.town.Town;
import org.springframework.core.convert.converter.Converter;

public class TownToEntityConverter implements Converter<TownDto, Town> {

    @Override
    public Town convert(TownDto townDto) {
        Town town = new Town();
        town.setId(townDto.getId());
        town.setTownName(townDto.getTownName());
        return town;
    }
}
