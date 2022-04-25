package by.sam_solutions.grigorieva.olga.backend.exception;

import lombok.Getter;

@Getter
public class UsernameAlreadyExists extends BusinessException {

    public UsernameAlreadyExists() {
        super("username.exists");
    }
}
