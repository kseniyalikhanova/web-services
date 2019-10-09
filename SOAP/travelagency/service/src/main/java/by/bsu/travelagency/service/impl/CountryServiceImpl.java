package by.bsu.travelagency.service.impl;

import by.bsu.travelagency.entity.Country;
import by.bsu.travelagency.repository.CountryRepository;
import by.bsu.travelagency.service.AbstractService;
import by.bsu.travelagency.service.CountryService;
import by.bsu.travelagency.specification.impl.CountryByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CountryServiceImpl extends AbstractService<Country> implements CountryService {

    private final CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean save(String name) {
        boolean isSaved = true;
        if (repository.findBySpecification(new CountryByName(name)).isEmpty()) {
            repository.save(new Country(name));
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public boolean update(Integer id, String name) {
        boolean isUpdated = true;
        if (repository.findBySpecification(new CountryByName(name)).isEmpty()) {
            Country country = repository.findById(id);
            country.setName(name);
            repository.update(country);
        } else {
            isUpdated = false;
        }
        return isUpdated;
    }
}
