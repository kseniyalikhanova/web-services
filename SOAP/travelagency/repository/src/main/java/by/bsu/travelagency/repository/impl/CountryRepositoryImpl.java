package by.bsu.travelagency.repository.impl;

import by.bsu.travelagency.entity.Country;
import by.bsu.travelagency.repository.AbstractRepository;
import by.bsu.travelagency.repository.CountryRepository;
import by.bsu.travelagency.util.QueryName;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("countryRepository")
public class CountryRepositoryImpl extends AbstractRepository<Country> implements CountryRepository {

    public CountryRepositoryImpl() {
        super.setClazz(Country.class);
    }

    @Override
    public List<Country> read() {
        List<Country> countries = entityManager.createNamedQuery(
                                        QueryName.FIND_ALL_COUNTRIES, Country.class)
                                    .getResultList();
        if (countries == null) {
            countries = new LinkedList<>();
        }
        return countries;
    }
}
