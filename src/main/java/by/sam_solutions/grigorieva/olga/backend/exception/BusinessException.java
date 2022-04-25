package by.sam_solutions.grigorieva.olga.backend.exception;

import by.sam_solutions.grigorieva.olga.backend.domain.localization.Messages;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;

@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String messageKey;

    public BusinessException(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getLocalizedMessage() {
        return Messages.getMessageForLocale(messageKey, LocaleContextHolder.getLocale());
    }
}
