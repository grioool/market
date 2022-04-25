package by.sam_solutions.grigorieva.olga.backend.repository.supply.product;

import by.sam_solutions.grigorieva.olga.backend.entity.SupplyProduct;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepository;

public interface SupplyProductRepository extends AbstractRepository<SupplyProduct> {

    SupplyProduct getByProduct(String name);
}
