package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.RoleDto;
import by.sam_solutions.grigorieva.olga.backend.dto.UserDto;
import by.sam_solutions.grigorieva.olga.backend.dto.UserRoleDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import by.sam_solutions.grigorieva.olga.backend.entity.TokenAuthentication;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.exception.RefreshTokenException;
import by.sam_solutions.grigorieva.olga.backend.service.user.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ConversionService conversionService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        logger.info("Getting users...");
        return new ResponseEntity<>(userService.getAll().stream()
                .map(user -> conversionService.convert(user, UserDto.class))
                .collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/admin/usersByPage")
    public TablePage<UserDto> getUsersByPage(@RequestParam Integer shift, @RequestParam Integer rowsPerPage) {
        logger.info("Getting users by page...");
        TablePage<User> page = userService.getUsersPerPage(shift, rowsPerPage);
        return new TablePage<>(
                page.getItems().stream()
                        .map(user -> conversionService.convert(user, UserDto.class))
                        .collect(toList()),
                page.getTotalCount(),
                page.getCurrentShift()
        );
    }

    @PostMapping("/admin/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        logger.info("Getting user...");
        User user = conversionService.convert(userDto, User.class);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").toUriString());
        return ResponseEntity.created(uri).body(conversionService.convert(userService.createUser(user), UserDto.class));
    }

    @PostMapping("/admin/roles")
    public ResponseEntity<RoleDto> createRole(@RequestBody @Valid RoleDto roleDto) {
        logger.info("Creating role...");
        Role role = conversionService.convert(roleDto, Role.class);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role").toUriString());
        return ResponseEntity.created(uri).body(conversionService.convert(userService.createRole(role), RoleDto.class));
    }

    @PostMapping("/admin/roles/adding")
    public ResponseEntity<?> addRoleToUser(@RequestBody @Valid UserRoleDto dto) {
        logger.info("Adding role to user...");
        return ResponseEntity.ok().body(userService.addRoleToUser(dto.getUsername(), dto.getRoleName()));
    }

    @GetMapping("/admin/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer id) {
        logger.info("Getting user...");
        return new ResponseEntity<>(conversionService.convert(userService.getById(id), UserDto.class), HttpStatus.OK);
    }

    @GetMapping("/users/information")
    public ResponseEntity<UserDto> getUserInformation(Principal principal) {
        logger.info("Getting user information ...");
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return new ResponseEntity<>(conversionService.convert(user, UserDto.class), HttpStatus.OK);
    }


    @GetMapping("/admin/roles")
    public ResponseEntity<Collection<String>> getRole(Principal principal) {
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return ResponseEntity.ok(user.getRoles().stream().map(Role::getRoleName).collect(toList()));
    }

    @PutMapping(value = "/admin/users")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        logger.info("Updating user...");
        User user = conversionService.convert(userDto, User.class);
        return new ResponseEntity<>(conversionService.convert(userService.update(user), UserDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/users/{userId}")
    public void delete(@PathVariable("userId") int id) {
        logger.info("Deleting users...");
        userService.delete(id);
        logger.error("Deleted.");
    }

    @GetMapping("/token/refreshing")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new RefreshTokenException();
        try {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            User user = userService.getByUsername(username);

            new ObjectMapper().writeValue(response.getOutputStream(),
                    TokenAuthentication.builder()
                            .accessToken(
                                    JWT.create()
                                            .withSubject(user.getUsername())
                                            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                                            .withClaim("roles", user.getRoles().stream().map(Role::getRoleName).collect(toList()))
                                            .sign(algorithm)
                            )
                            .refreshToken(refresh_token)
                            .build());

        } catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            response.sendError(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            logger.error(exception.getMessage());
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }

}

