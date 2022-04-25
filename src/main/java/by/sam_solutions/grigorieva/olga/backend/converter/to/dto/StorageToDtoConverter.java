package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.CountryDto;
import by.sam_solutions.grigorieva.olga.backend.dto.StorageDto;
import by.sam_solutions.grigorieva.olga.backend.dto.TownDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class StorageToDtoConverter implements Converter<Storage, StorageDto> {

    private final ConversionService conversionService;

    @Override
    public StorageDto convert(Storage storage) {
        StorageDto dto = new StorageDto();
        dto.setId(storage.getId());
        dto.setCountry(conversionService.convert(storage.getCountry(), CountryDto.class));
        dto.setTown(conversionService.convert(storage.getTown(), TownDto.class));
        return dto;
    }
}
