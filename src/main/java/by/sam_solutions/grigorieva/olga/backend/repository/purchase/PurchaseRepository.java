package by.sam_solutions.grigorieva.olga.backend.repository.purchase;

import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepository;

import java.util.List;

public interface PurchaseRepository extends AbstractRepository<Purchase> {

    List<Purchase> getByUser(User user);
}
