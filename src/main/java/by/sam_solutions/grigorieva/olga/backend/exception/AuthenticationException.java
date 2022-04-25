package by.sam_solutions.grigorieva.olga.backend.exception;

import by.sam_solutions.grigorieva.olga.backend.domain.localization.Messages;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Locale;

public class AuthenticationException extends UsernameNotFoundException {

    private Locale locale;

    public AuthenticationException(String messageKey) {
        this(messageKey, Locale.getDefault());
    }

    public AuthenticationException(String messageKey, Locale locale) {
        super(messageKey);
        locale = Locale.getDefault();
    }

    public String getLocalizedMessage() {
        return Messages.getMessageForLocale(getMessage(), locale);
    }
}
