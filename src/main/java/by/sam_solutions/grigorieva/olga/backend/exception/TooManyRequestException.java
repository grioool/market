package by.sam_solutions.grigorieva.olga.backend.exception;

public class TooManyRequestException extends BusinessException {

    public TooManyRequestException() {
        super("too.many.request");
    }
}
