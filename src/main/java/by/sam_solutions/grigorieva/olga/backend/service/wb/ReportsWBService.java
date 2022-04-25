package by.sam_solutions.grigorieva.olga.backend.service.wb;

import by.sam_solutions.grigorieva.olga.backend.domain.constraint.DatePeriod;
import by.sam_solutions.grigorieva.olga.backend.domain.supplier.oper.name.SupplierOperName;
import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.ReportWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportsWBService extends WBService<ReportWBDto> {

    public ReportsWBService(@Value("${url.reports}") String url) {
        super(url);
    }

    public List<ReportWBDto> getByDatePeriod(DatePeriod period, User user) {
        return getByWBKey(
                user,
                uriComponentsBuilder -> uriComponentsBuilder
                        .queryParam("dateFrom", period.getFrom().toLocalDate().toString())
                        .queryParam("dateTo", period.getTo().toLocalDate().toString()),
                new ParameterizedTypeReference<List<ReportWBDto>>() {
                }
        );
    }

    public TablePage<ReportWBDto> getByShift(int shift, int rowsPerPage, DatePeriod period, User user) {
        List<ReportWBDto> entities = getByDatePeriod(period, user);
        if (entities == null) return TablePage.blank();

        entities = entities.stream()
                .filter(reportWBDto -> SupplierOperName.SALE.getValue().equals(reportWBDto.getSupplier_oper_name()))
                .collect(Collectors.toList());
        return TablePage.slice(entities, shift, rowsPerPage);
    }
}
