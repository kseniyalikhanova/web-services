package com.epam.travelagency.service;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.enumeration.TourType;

import java.math.BigDecimal;
import java.sql.Date;

public interface TourService extends Service<Tour> {

    void create(String photo, Date date,
              short duration, String description,
              BigDecimal cost, TourType tourType,
              Integer hotelId, Integer countryId);

    void update(Integer id, String photo, Date date,
                short duration, String description,
                BigDecimal cost, Integer hotelId);
}
