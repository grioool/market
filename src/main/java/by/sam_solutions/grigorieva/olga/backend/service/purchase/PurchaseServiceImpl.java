package by.sam_solutions.grigorieva.olga.backend.service.purchase;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.purchase.PurchaseRepository;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl extends AbstractServiceImpl<Purchase> implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> getByUser(User user) {
        return purchaseRepository.getByUser(user);
    }

    @Override
    public TablePage<Purchase> getPurchasesPerPage(User user, int shift, int rowsPerPage) {
        List<Purchase> purchases = purchaseRepository.getByUser(user);
        return TablePage.slice(purchases, shift, rowsPerPage);
    }
}
