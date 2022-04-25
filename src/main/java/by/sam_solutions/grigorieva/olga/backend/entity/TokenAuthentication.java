package by.sam_solutions.grigorieva.olga.backend.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenAuthentication {

    private final String accessToken;

    private final String refreshToken;

}
