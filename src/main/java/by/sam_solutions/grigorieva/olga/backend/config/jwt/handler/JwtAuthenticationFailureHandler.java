package by.sam_solutions.grigorieva.olga.backend.config.jwt.handler;

import by.sam_solutions.grigorieva.olga.backend.domain.localization.Messages;
import by.sam_solutions.grigorieva.olga.backend.dto.ExceptionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(UNAUTHORIZED.value());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_16.toString());

        String message = Messages.getMessageForLocale("bad.credentials", httpServletRequest.getLocale());
        httpServletResponse.getWriter().write(mapper.writeValueAsString(new ExceptionDto(message)));
    }
}
