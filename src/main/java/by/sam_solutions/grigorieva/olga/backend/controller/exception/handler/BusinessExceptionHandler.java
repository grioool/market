package by.sam_solutions.grigorieva.olga.backend.controller.exception.handler;

import by.sam_solutions.grigorieva.olga.backend.domain.localization.Messages;
import by.sam_solutions.grigorieva.olga.backend.dto.ExceptionDto;
import by.sam_solutions.grigorieva.olga.backend.exception.*;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@RequiredArgsConstructor
public class BusinessExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);
    private final ConversionService conversionService;

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ExceptionDto> handleAuthorizationException(AuthenticationException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(value = UsernameAlreadyExists.class)
    public ResponseEntity<ExceptionDto> handleUserAlreadyExists(UsernameAlreadyExists e) {
        logError(e);
        return ResponseEntity.badRequest().body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(value = EmailAlreadyExists.class)
    public ResponseEntity<ExceptionDto> handleEmailAlreadyExists(EmailAlreadyExists e) {
        logError(e);
        return ResponseEntity.badRequest().body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ExceptionDto> handleBusinessException(BusinessException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(value = TokenException.class)
    public ResponseEntity<ExceptionDto> handleTokenInvalid(TokenException e) {
        logError(e);
        return ResponseEntity.badRequest().body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ExceptionDto> handleNotImplementedExceptions(HttpRequestMethodNotSupportedException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ExceptionDto(Messages.getMessageForLocale("method.not.supported", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<ExceptionDto> handlerInternalExceptions(IOException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDto(Messages.getMessageForLocale("fatal.error", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionDto> handleBadCredentialsException(BadCredentialsException e) {
        logError(e);
        return ResponseEntity.badRequest().body(new ExceptionDto(Messages.getMessageForLocale("bad.credentials", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleUsernameNotFoundException(UsernameNotFoundException e) {
        logError(e);
        return ResponseEntity.badRequest()
                .body(new ExceptionDto(Messages.getMessageForLocale("bad.credentials", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(UpgradeRequiredException.class)
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public ResponseEntity<ExceptionDto> handleBadCredentialsException(UpgradeRequiredException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(TooManyRequestException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity<ExceptionDto> handleManyRequestException(TooManyRequestException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleConversionException(ConversionNotSupportedException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new ExceptionDto(Messages.getMessageForLocale("fatal.error", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleMethodNotValidException(MethodArgumentNotValidException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("not.valid.argument", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleNullPointerException(NullPointerException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("fatal.error", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(LocalizationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleLocalizationException(LocalizationException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(conversionService.convert(e, ExceptionDto.class));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleDatabaseIntegrityException(DataIntegrityViolationException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("data.integrity.error", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleConstraintViolationException(ConstraintViolationException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("not.valid.argument", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePSQLException(PSQLException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("not.valid.argument", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("fatal.error", LocaleContextHolder.getLocale())));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleIAccessDeniedException(AccessDeniedException e) {
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(Messages.getMessageForLocale("access.denied.error", LocaleContextHolder.getLocale())));
    }

    private void logError(Exception e) {
        logger.error(e.getClass() + ": " + e.getMessage());
    }

}
