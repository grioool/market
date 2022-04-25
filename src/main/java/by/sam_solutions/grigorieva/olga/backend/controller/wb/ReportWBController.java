package by.sam_solutions.grigorieva.olga.backend.controller.wb;

import by.sam_solutions.grigorieva.olga.backend.domain.constraint.DatePeriod;
import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.ReportDto;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.ReportWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.wb.ReportsWBService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class ReportWBController {
    private final ReportsWBService reportsService;
    private final ConversionService conversionService;
    private final Logger logger = LoggerFactory.getLogger(SaleWBController.class);

    @GetMapping("/reports")
    public ResponseEntity<List<ReportDto>> getReportsWB(@RequestParam
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                    LocalDateTime dateFrom,
                                                        @RequestParam
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                    LocalDateTime dateTo,
                                                        Principal principal) {
        logger.info("Getting reports...");
        return ResponseEntity.ok().body(
                reportsService.getByDatePeriod(new DatePeriod(dateFrom, dateTo), getUser(principal)).stream()
                        .map(reportWBDto -> conversionService.convert(reportWBDto, ReportDto.class))
                        .collect(toList())
        );
    }

    @GetMapping("/reportsByPage")
    public ResponseEntity<TablePage<ReportDto>> getReportsWBByPage(@RequestParam
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                               LocalDateTime dateFrom,
                                                                   @RequestParam
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                           LocalDateTime dateTo,
                                                                   @RequestParam Integer shift,
                                                                   @RequestParam Integer rowsPerPage,
                                                                   Principal principal) {
        logger.info("Getting reports by page...");
        TablePage<ReportWBDto> tablePage = reportsService.getByShift(shift, rowsPerPage, new DatePeriod(dateFrom, dateTo), getUser(principal));
        return ResponseEntity.ok(
                new TablePage<>(
                        tablePage.getItems().stream()
                                .map(reportWBDto -> conversionService.convert(reportWBDto, ReportDto.class))
                                .collect(toList()),
                        tablePage.getTotalCount(),
                        tablePage.getCurrentShift()
                ));
    }

    private User getUser(Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
