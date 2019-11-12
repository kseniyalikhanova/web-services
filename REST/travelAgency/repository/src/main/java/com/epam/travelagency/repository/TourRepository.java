package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.enumeration.TourType;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.stereotype.Repository
public interface TourRepository extends Repository<Tour> {
    List<Tour> getByCountry(Country country);

    List<Tour> getAllByCost(BigDecimal cost);

    List<Tour> getAllByDuration(short duration);

    List<Tour> getAllByTourType(TourType tourType);
}
