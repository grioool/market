package by.sam_solutions.grigorieva.olga.backend.repository.purchase;

import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepositoryImpl extends AbstractRepositoryImpl<Purchase> implements PurchaseRepository {

    @Override
    public List<Purchase> getByUser(User user) {
        return entityManager.createQuery("SELECT s FROM Purchase s WHERE s.user = :user")
                .setParameter("user", user)
                .getResultList();
    }
}