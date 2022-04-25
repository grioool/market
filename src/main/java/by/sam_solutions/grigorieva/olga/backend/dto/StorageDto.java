package by.sam_solutions.grigorieva.olga.backend.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Validated
public class StorageDto {

    @Null
    private Integer id;

    @NotNull
    private CountryDto country;

    @NotNull
    private TownDto town;

}
