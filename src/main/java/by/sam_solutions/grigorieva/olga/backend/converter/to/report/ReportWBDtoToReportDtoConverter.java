package by.sam_solutions.grigorieva.olga.backend.converter.to.report;

import by.sam_solutions.grigorieva.olga.backend.dto.ReportDto;
import by.sam_solutions.grigorieva.olga.backend.dto.SupplyTableRowDto;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.ReportWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Supply;
import by.sam_solutions.grigorieva.olga.backend.service.profit.ProfitService;
import by.sam_solutions.grigorieva.olga.backend.service.supply.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class ReportWBDtoToReportDtoConverter implements Converter<ReportWBDto, ReportDto> {

    private final ConversionService conversionService;

    private final SupplyService supplyService;

    private final ProfitService profitService;

    @Override
    public ReportDto convert(ReportWBDto reportWBDto) {
        Supply supply = supplyService.getByWildberriesIdAndProductName(reportWBDto.getSa_name(), reportWBDto.getGi_id().intValue());
        SupplyTableRowDto supplyTableRowDto = supply == null
                ? SupplyTableRowDto.blank(reportWBDto.getRealizationreport_id())
                : conversionService.convert(supply.getProduct(reportWBDto.getSa_name()), SupplyTableRowDto.class);

        supplyTableRowDto.setWildberriesId(reportWBDto.getGi_id().intValue());
        ReportDto reportDto = new ReportDto();
        reportDto.setId(reportWBDto.getRealizationreport_id());
        reportDto.setOrderNumber(reportWBDto.getRid());
        reportDto.setSupply(supplyTableRowDto);
        reportDto.setName(reportWBDto.getSa_name());
        reportDto.setOrderPrice(reportWBDto.getRetail_price_withdisc_rub());
        reportDto.setProceeds(reportWBDto.getPpvz_for_pay());
        reportDto.setLogistics(reportWBDto.getDelivery_rub());
        reportDto.setCostPrice(supplyTableRowDto.getCostPrice());
        reportDto.setCommission(reportWBDto.getPpvz_sales_commission());
        reportDto.setCommissionPerCent(reportWBDto.getCommission_percent());
        reportDto.setDateOrder(reportWBDto.getOrder_dt().toLocalDateTime().toString());
        reportDto.setDateSale(reportWBDto.getSale_dt().toLocalDateTime().toString());
        reportDto.setProfit(profitService.getProfit(reportDto));

        return reportDto;
    }
}
