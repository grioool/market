package by.sam_solutions.grigorieva.olga.backend.service.purchase;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractService;

import java.util.List;

public interface PurchaseService extends AbstractService<Purchase> {

    List<Purchase> getByUser(User user);

    TablePage<Purchase> getPurchasesPerPage(User user, int shift, int rowsPerPage);

}
