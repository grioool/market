package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class UserRegistrationDto {

    @NotNull
    @CustomPattern(patternKey = "username.regexp", message = "username.invalid")
    private String username;

    @NotNull
    @CustomPattern(patternKey = "password.regexp", message = "password.invalid")
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    @CustomPattern(patternKey = "nameCompany.regexp", message = "nameCompany.invalid")
    private String nameCompany;

    @NotNull
    @CustomPattern(patternKey = "wbKey.regexp", message = "wbKey.invalid")
    private String wbKey;

}
