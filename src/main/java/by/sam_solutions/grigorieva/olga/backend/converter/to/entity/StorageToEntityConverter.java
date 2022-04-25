package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.StorageDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Storage;
import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import by.sam_solutions.grigorieva.olga.backend.entity.town.Town;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class StorageToEntityConverter implements Converter<StorageDto, Storage> {

    private final ConversionService conversionService;

    @Override
    public Storage convert(StorageDto storageDto) {
        Storage storage = new Storage();
        storage.setId(storageDto.getId());
        storage.setCountry(conversionService.convert(storageDto.getCountry(), Country.class));
        storage.setTown(conversionService.convert(storageDto.getTown(), Town.class));
        return storage;
    }
}
