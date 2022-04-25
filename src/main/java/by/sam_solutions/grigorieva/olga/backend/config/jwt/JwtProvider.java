package by.sam_solutions.grigorieva.olga.backend.config.jwt;

import by.sam_solutions.grigorieva.olga.backend.entity.TokenAuthentication;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final Integer accessTokenLifetime;

    private final Integer refreshTokenLifetime;

    private final Algorithm algorithm;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.lifetime.accessToken}") Integer accessTokenLifetime,
                       @Value("${jwt.lifetime.refreshToken}") Integer refreshTokenLifetime) {
        algorithm = Algorithm.HMAC512(secret);
        this.accessTokenLifetime = accessTokenLifetime;
        this.refreshTokenLifetime = refreshTokenLifetime;
    }

    public TokenAuthentication generateToken(User user) {
        return TokenAuthentication.builder()
                .accessToken(
                        JWT.create()
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenLifetime * 60 * 1000))
                                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                                .sign(algorithm)
                )
                .refreshToken(
                        JWT.create()
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenLifetime * 60 * 1000))
                                .sign(algorithm)
                )
                .build();
    }

    public TokenAuthentication refreshToken(String refreshToken, User user) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        return TokenAuthentication.builder()
                .accessToken(
                        JWT.create()
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                                .sign(algorithm)
                )
                .refreshToken(refreshToken)
                .build();
    }

}
