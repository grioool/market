package by.sam_solutions.grigorieva.olga.backend.repository.report;

import by.sam_solutions.grigorieva.olga.backend.entity.Report;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepository;

import java.util.List;

public interface ReportRepository extends AbstractRepository<Report> {

    List<Report> getByUser(User user);
}
