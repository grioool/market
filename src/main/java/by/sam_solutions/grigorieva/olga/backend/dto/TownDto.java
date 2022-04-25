package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import by.sam_solutions.grigorieva.olga.backend.entity.town.TownName;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Validated
public class TownDto {

    @Null
    private Integer id;

    @NotNull
    @CustomPattern(patternKey = "field.letters.regexp", message = "field.letters.invalid")
    private TownName townName;
}
