package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.dto.ReportDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Report;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Validated
public class ReportController {

    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ConversionService conversionService;

    @GetMapping(value = "/reports/{reportId}")
    public ResponseEntity<ReportDto> getReport(@PathVariable("reportId") int id) {
        logger.info("Getting report...");
        return new ResponseEntity<>(conversionService.convert(reportService.getById(id), ReportDto.class), HttpStatus.OK);
    }

    @PostMapping(value = "/reports")
    public ResponseEntity<ReportDto> create(@RequestBody @Valid ReportDto reportDto, Principal principal) {
        logger.info("Creating report...");
        Report report = conversionService.convert(reportDto, Report.class);
        report.setUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return new ResponseEntity<>(conversionService.convert(reportService.create(report), ReportDto.class), HttpStatus.OK);
    }

    @PutMapping(value = "/reports")
    public ResponseEntity<ReportDto> update(@RequestBody ReportDto reportDto, Principal principal) {
        logger.info("Updating report...");
        Report report = conversionService.convert(reportDto, Report.class);
        report.setUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return new ResponseEntity<>(conversionService.convert(reportService.update(report), ReportDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/reports/{reportId}")
    public void delete(@PathVariable("reportId") int id) {
        logger.info("Deleting report...");
        reportService.delete(id);
        logger.info("Deleted.");
    }

}
