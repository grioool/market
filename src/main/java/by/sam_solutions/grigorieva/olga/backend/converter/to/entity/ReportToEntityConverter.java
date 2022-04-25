package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.ReportDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Report;
import by.sam_solutions.grigorieva.olga.backend.entity.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

@RequiredArgsConstructor
public class ReportToEntityConverter implements Converter<ReportDto, Report> {

    private final ConversionService conversionService;

    @Override
    public Report convert(ReportDto reportDto) {
        Report report = new Report();
        report.setId(reportDto.getId());
        report.setSupply(conversionService.convert(reportDto.getSupply(), Supply.class));
        report.setName(reportDto.getName());
        report.setCommission(reportDto.getCommission());
        report.setCommissionPerCent(reportDto.getCommissionPerCent());
        report.setCostPrice(reportDto.getCostPrice());
        report.setDateOrder(Timestamp.valueOf(reportDto.getDateOrder()));
        report.setDateSale(Timestamp.valueOf(reportDto.getDateSale()));
        report.setLogistics(reportDto.getLogistics());
        report.setOrderNumber(reportDto.getOrderNumber());
        report.setOrderPrice(reportDto.getOrderPrice());
        report.setProceeds(reportDto.getProceeds());
        report.setProfit(reportDto.getProfit());
        return report;
    }
}
