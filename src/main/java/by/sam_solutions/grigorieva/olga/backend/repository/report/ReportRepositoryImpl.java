package by.sam_solutions.grigorieva.olga.backend.repository.report;

import by.sam_solutions.grigorieva.olga.backend.entity.Report;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepositoryImpl extends AbstractRepositoryImpl<Report> implements ReportRepository {

    @Override
    public List<Report> getByUser(User user) {
        return entityManager.createQuery("SELECT s FROM Report s WHERE s.user = :user")
                .setParameter("user", user)
                .getResultList();
    }
}
