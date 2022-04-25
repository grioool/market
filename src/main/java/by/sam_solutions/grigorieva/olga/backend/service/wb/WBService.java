package by.sam_solutions.grigorieva.olga.backend.service.wb;

import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.exception.TooManyRequestException;
import by.sam_solutions.grigorieva.olga.backend.exception.UpgradeRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class WBService<Entity> {

    private final String url;

    private final RestTemplate restTemplate = new RestTemplate();

    protected List<Entity> getByWBKey(User user,
                                      Consumer<UriComponentsBuilder> uriConfigurator,
                                      ParameterizedTypeReference<List<Entity>> typeReference) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        headers.set(HttpHeaders.CONNECTION, "keep-alive");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", user.getWildBerriesKeys());
        uriConfigurator.accept(uriBuilder);

        try {
            ResponseEntity<List<Entity>> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    typeReference
            );

            return response.getBody();
        }
        catch(HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.UPGRADE_REQUIRED) throw new UpgradeRequiredException();
            if(e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) throw new TooManyRequestException();
            throw e;
        }

    }
}
