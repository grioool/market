package by.sam_solutions.grigorieva.olga.backend.service.profit;

import by.sam_solutions.grigorieva.olga.backend.dto.ReportDto;
import org.springframework.stereotype.Service;

@Service
public class ProfitService {

    public Double getProfit(ReportDto reportDto) {
        return reportDto.getProceeds() - reportDto.getLogistics() - reportDto.getCostPrice();
    }
}
