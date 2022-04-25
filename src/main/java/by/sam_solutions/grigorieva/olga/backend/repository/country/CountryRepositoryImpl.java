package by.sam_solutions.grigorieva.olga.backend.repository.country;

import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import by.sam_solutions.grigorieva.olga.backend.entity.country.CountryName;
import by.sam_solutions.grigorieva.olga.backend.repository.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepositoryImpl extends AbstractRepositoryImpl<Country> implements CountryRepository {

    public Country findByName(CountryName name) {
        return entityManager.createQuery(
                        "SELECT c from Country c WHERE c.countryName = :countryName", Country.class
                )
                .setParameter("countryName", name)
                .getSingleResult();
    }
}
