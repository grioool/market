package by.sam_solutions.grigorieva.olga.backend.service.profit;

import by.sam_solutions.grigorieva.olga.backend.config.HibernateConfiguration;
import by.sam_solutions.grigorieva.olga.backend.dto.ReportDto;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {HibernateConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfitServiceTest extends TestCase {

    @Autowired
    private ProfitService profitService;

    @Test
    public void testGetProfit() {

        ReportDto reportDto = new ReportDto();
        reportDto.setProceeds(5.0);
        reportDto.setLogistics(3.0);
        reportDto.setCostPrice(1.0);

        Double result = profitService.getProfit(reportDto);

        Assertions.assertThat(result).isEqualTo(1);
    }
}