package by.sam_solutions.grigorieva.olga.backend.exception;

public class TokenException extends BusinessException{

    public TokenException() {
        super("token.invalid");
    }
}
