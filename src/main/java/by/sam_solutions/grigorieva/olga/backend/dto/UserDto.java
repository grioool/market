package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Validated
public class UserDto {

    @Null
    private int id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @CustomPattern(patternKey = "field.letters.regexp", message = "field.letters.invalid")
    private String password;

    @NotNull
    @CustomPattern(patternKey = "field.letters.regexp", message = "field.letters.invalid")
    private String username;

    @NotNull
    @CustomPattern(patternKey = "field.letters.regexp", message = "field.letters.invalid")
    private String nameCompany;

    @NotNull
    @CustomPattern(patternKey = "field.letters.regexp", message = "field.letters.invalid")
    private String wildBerriesKeys;

    @NotNull
    private Boolean isBlocked;

    @NotNull
    private Boolean isSubscribed;
}
