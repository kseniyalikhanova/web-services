package com.epam.travelagency.service.impl;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.repository.CountryRepository;
import com.epam.travelagency.service.AbstractService;
import com.epam.travelagency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CountryServiceImpl extends AbstractService<Country, CountryRepository> implements CountryService {

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        super(repository);
    }

    @Override
    public boolean create(String name) {
        boolean isSaved = true;
        if (repository.getByName(name) == null) {
            repository.save(new Country(name));
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public boolean update(Integer id, String name) {
        boolean isUpdated = true;
        if (repository.getByName(name) == null) {
            Country country = getById(id);
            country.setName(name);
            repository.save(country);
        } else {
            isUpdated = false;
        }
        return isUpdated;
    }
}
