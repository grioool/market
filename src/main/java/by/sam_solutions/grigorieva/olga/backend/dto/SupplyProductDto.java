package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class SupplyProductDto {

    @NotNull
    private Integer id;

    @NotNull
    private SupplyTableRowDto supply;

    @NotNull
    private String product;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String amount;
}
