package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.ExceptionDto;
import by.sam_solutions.grigorieva.olga.backend.exception.BusinessException;
import org.springframework.core.convert.converter.Converter;

public class ExceptionToDtoConverter implements Converter<BusinessException, ExceptionDto> {

    @Override
    public ExceptionDto convert(BusinessException e) {
        return new ExceptionDto(e.getLocalizedMessage());
    }
}
