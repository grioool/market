package by.sam_solutions.grigorieva.olga.backend.exception;

public class EmailAlreadyExists extends BusinessException {

    public EmailAlreadyExists() {
        super("email.exists");
    }
}
