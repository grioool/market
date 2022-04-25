package by.sam_solutions.grigorieva.olga.backend.service.supply;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.entity.Supply;
import by.sam_solutions.grigorieva.olga.backend.entity.SupplyProduct;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.repository.supply.SupplyRepository;
import by.sam_solutions.grigorieva.olga.backend.repository.supply.product.SupplyProductRepository;
import by.sam_solutions.grigorieva.olga.backend.service.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplyServiceImpl extends AbstractServiceImpl<Supply> implements SupplyService {

    private final SupplyRepository supplyRepository;

    private final SupplyProductRepository supplyProductRepository;

    @Override
    public void addSupplyProduct(SupplyProduct supplyProduct) {
        Supply supply = supplyRepository.getByWildberriesId(supplyProduct.getSupply().getId());
        if (supply == null) {
            create(supplyProduct.getSupply());
            return;
        }
        supply.addSupplyProduct(supplyProduct);
        update(supply);
    }

    @Override
    public void updateSupplyProduct(SupplyProduct supplyProduct) {
        supplyProduct.getSupply().setId(supplyRepository.getByWildberriesId(supplyProduct.getSupply().getWildberriesId()).getId());
        supplyProductRepository.update(supplyProduct);
    }

    @Override
    public void deleteSupplyProduct(Integer id) {
        SupplyProduct supplyProduct = supplyProductRepository.getById(id);
        Supply supply = supplyProduct.getSupply();
        if (supply.getSupplyProducts().size() == 1) {
            supplyRepository.delete(supply.getId());
            return;
        }
        supply.removeProduct(supplyProduct);
    }

    @Override
    public List<Supply> getByUser(User user) {
        return supplyRepository.getByUser(user);
    }

    @Override
    public TablePage<SupplyProduct> getSupplyProductsPerPage(User user, int shift, int rowsPerPage) {
        List<Supply> supplies = supplyRepository.getByUser(user);
        return TablePage.slice(
                supplies.stream()
                        .flatMap(supply -> supply.getSupplyProducts().stream())
                        .collect(Collectors.toList()),
                shift,
                rowsPerPage
        );
    }

    @Override
    public Supply getByWildberriesIdAndProductName(String product, int id) {
        Supply supply = supplyRepository.getByWildberriesId(id);
        if (supply == null) {
            return null;
        }

        if (supply.getProduct(product) == null) return null;
        return supply;
    }

    @Override
    public Supply getByWildberriesId(int id) {
        return supplyRepository.getByWildberriesId(id);
    }
}
