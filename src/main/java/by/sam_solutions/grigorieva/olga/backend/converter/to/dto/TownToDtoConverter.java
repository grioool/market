package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.TownDto;
import by.sam_solutions.grigorieva.olga.backend.entity.town.Town;
import org.springframework.core.convert.converter.Converter;

public class TownToDtoConverter implements Converter<Town, TownDto> {

    @Override
    public TownDto convert(Town town) {
        TownDto dto = new TownDto();
        dto.setId(town.getId());
        dto.setTownName(town.getTownName());
        return dto;
    }
}
