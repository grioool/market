package by.sam_solutions.grigorieva.olga.backend.controller.registration;

import by.sam_solutions.grigorieva.olga.backend.dto.UserRegistrationDto;
import by.sam_solutions.grigorieva.olga.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping("/registration")
    public void register(@RequestBody @Valid UserRegistrationDto userDto) {
        logger.info("New user registration...");
        userService.register(userDto);
        logger.info("Registered.");
    }
}
