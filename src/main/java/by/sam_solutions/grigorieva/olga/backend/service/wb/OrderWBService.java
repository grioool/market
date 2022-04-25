package by.sam_solutions.grigorieva.olga.backend.service.wb;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.OrderWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderWBService extends WBService<OrderWBDto> {

    public OrderWBService(@Value("${url.orders}") String url) {
        super(url);
    }

    public List<OrderWBDto> getByDateFrom(LocalDateTime from, User user) {
        return getByWBKey(
                user,
                uriComponentsBuilder -> uriComponentsBuilder
                        .queryParam("dateFrom", from.toLocalDate().toString())
                        .queryParam("flag", "0"),
                new ParameterizedTypeReference<List<OrderWBDto>>() {}
        );
    }

    public TablePage<OrderWBDto> getByShift(int shift, int rowsPerPage, LocalDateTime from, User user) {
        List<OrderWBDto> entities = getByDateFrom(from, user);
        return TablePage.slice(entities, shift, rowsPerPage);
    }
}