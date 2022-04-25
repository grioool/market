package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.domain.password.Utility;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.exception.AuthenticationException;
import by.sam_solutions.grigorieva.olga.backend.exception.TokenException;
import by.sam_solutions.grigorieva.olga.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class ForgotPasswordController {

    @Value("${username.mail.sender}")
    private String username;

    private final JavaMailSender mailSender;

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/password/forgetting")
    public void processForgotPassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            userService.updateResetPassword(password, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/password/resetting?token=" + token;
            sendEmail(email, resetPasswordLink);

        } catch (AuthenticationException ex) {
           logger.error("", ex);
        } catch (UnsupportedEncodingException | MessagingException e) {
          logger.error("Error while sending email", e);
        }

    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(username, "AutoMarket Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested resetting your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email, if you do remember your password, "
                + "or you haven't made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/password/forgetting/password/resetting")
    public String processResetPassword(@Param(value = "token") String token) {

        User user = userService.getByResetPasswordToken(token);

        if (user == null) {
            throw new TokenException();
        } else {
            String password = user.getResetPassword();
            userService.updatePassword(user, password);
        }
        return "You successfully changed password!";
    }
}