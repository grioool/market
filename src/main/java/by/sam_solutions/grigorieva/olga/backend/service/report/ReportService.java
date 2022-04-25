package by.sam_solutions.grigorieva.olga.backend.service.report;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.entity.Report;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractService;

import java.util.List;

public interface ReportService extends AbstractService<Report> {

    List<Report> getByUser(User user);

    TablePage<Report> getReportsPerPage(User user, int shift, int rowsPerPage);

}
