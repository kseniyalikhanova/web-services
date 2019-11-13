package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.enumeration.TourType;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.stereotype.Repository
public interface TourRepository extends Repository<Tour> {
    List<Tour> getByCountry(Country country);

    List<Tour> getByCost(BigDecimal cost);

    List<Tour> getByDuration(short duration);

    List<Tour> getByTourType(TourType tourType);

    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.tour WHERE id=?",
            nativeQuery = true)
    short isArchival(Integer id);

    @Override
    @Query(value = "UPDATE travel_agency.tour SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);
}
