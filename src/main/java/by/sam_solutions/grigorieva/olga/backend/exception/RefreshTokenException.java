package by.sam_solutions.grigorieva.olga.backend.exception;

public class RefreshTokenException extends BusinessException {

    public RefreshTokenException() {
        super("refresh.token.invalid");
    }
}
