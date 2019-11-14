package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Tour;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TourRepository extends Repository<Tour> {
    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.tour WHERE id=?",
            nativeQuery = true)
    Short isArchival(Integer id);

    @Override
    @Modifying
    @Query(value = "UPDATE travel_agency.tour SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);

    @Query(value = "SELECT id FROM travel_agency.tour WHERE country_id=?",
            nativeQuery = true)
    List<Integer> getToursIdByCountryId(Integer countryId);

    List<Tour> getAllByCountry(Country country);
}
