package by.sam_solutions.grigorieva.olga.backend.repository.supply.product;

import by.sam_solutions.grigorieva.olga.backend.entity.SupplyProduct;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class SupplyProductRepositoryImpl extends AbstractRepositoryImpl<SupplyProduct> implements SupplyProductRepository {

    @Override
    public SupplyProduct getByProduct(String product) {
        try {
            return entityManager.createQuery(
                            "SELECT s from SupplyProduct s WHERE s.product = :product", SupplyProduct.class
                    )
                    .setParameter("product", product)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
