package by.sam_solutions.grigorieva.olga.backend.service.wb;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.SaleWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleWBService extends WBService<SaleWBDto> {

    public SaleWBService(@Value("${url.sales}") String url) {
        super(url);
    }

    public List<SaleWBDto> getByDateFrom(LocalDateTime dateFrom, User user) {
        return getByWBKey(
                user,
                uriComponentsBuilder -> uriComponentsBuilder
                        .queryParam("dateFrom", dateFrom.toLocalDate().toString())
                        .queryParam("flag", "0"),
                new ParameterizedTypeReference<List<SaleWBDto>>() {}
        );
    }

    public TablePage<SaleWBDto> getByShift(int shift, int rowsPerPage, LocalDateTime dateFrom, User user) {
        List<SaleWBDto> entities = getByDateFrom(dateFrom, user);
        return TablePage.slice(entities, shift, rowsPerPage);
    }
}
