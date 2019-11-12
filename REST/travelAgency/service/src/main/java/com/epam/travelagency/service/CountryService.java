package com.epam.travelagency.service;

import com.epam.travelagency.entity.Country;

public interface CountryService extends Service<Country> {

    boolean create(String name);

    boolean update(Integer id, String name);
}
