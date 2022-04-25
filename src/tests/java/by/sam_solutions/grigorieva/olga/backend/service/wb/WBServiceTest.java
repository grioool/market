package by.sam_solutions.grigorieva.olga.backend.service.wb;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.config.WebConfig;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.SaleWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HibernateConfiguration.class, WebConfig.class})
public class WBServiceTest extends TestCase {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private SaleWBService saleWBService;

    @Value("${url.sales}")
    private String url;

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        SaleWBDto emp = new SaleWBDto();
        Long orderId = 200L;
        emp.setOrderId(orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        headers.set(HttpHeaders.CONNECTION, "keep-alive");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        User user = new User();
        user.setWildBerriesKeys("");
        LocalDateTime dateFrom = LocalDateTime.now();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("dateFrom", dateFrom.toLocalDate().toString())
                .queryParam("flag", "0")
                .queryParam("key", user.getWildBerriesKeys());

        Mockito
                .when(restTemplate.exchange(
                        uriBuilder.toUriString(),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<SaleWBDto>>() {
                        }
                ))
                .thenReturn(ResponseEntity.ok(List.of(emp)));

        List<SaleWBDto> sales = saleWBService.getByDateFrom(dateFrom, user);
        Assertions.assertThat(sales).isNotEmpty();
        Assertions.assertThat(sales.get(0).getOrderId()).isEqualTo(orderId);
    }

    public void testGetByWBKey() {
    }
}