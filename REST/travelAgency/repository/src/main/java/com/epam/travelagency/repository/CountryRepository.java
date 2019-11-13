package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Country;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface CountryRepository extends Repository<Country> {
    Country getByName(String name);

    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.country WHERE id=?",
            nativeQuery = true)
    Short isArchival(Integer id);

    @Override
    @Query(value = "UPDATE travel_agency.country SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);
}