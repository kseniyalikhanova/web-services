package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Country;

public interface CountryService extends Service<Country> {

    boolean save(String name);

    boolean update(Integer id, String name);
}
