package by.sam_solutions.grigorieva.olga.backend.service.report;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.entity.Report;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.report.ReportRepository;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl extends AbstractServiceImpl<Report> implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<Report> getByUser(User user) {
        return reportRepository.getByUser(user);
    }

    @Override
    public TablePage<Report> getReportsPerPage(User user, int shift, int rowsPerPage) {
        List<Report> reports = reportRepository.getByUser(user);
        return TablePage.slice(reports, shift, rowsPerPage);
    }
}
