package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class UserLoginDto {

    @NotNull
    @CustomPattern(patternKey = "field.letters.regexp", message = "field.letters.invalid")
    private String username;

    @NotNull
    private String password;
}
