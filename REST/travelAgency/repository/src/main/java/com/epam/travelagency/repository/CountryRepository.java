package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Country;

@org.springframework.stereotype.Repository
public interface CountryRepository extends Repository<Country> {
    Country getByName(String name);
}