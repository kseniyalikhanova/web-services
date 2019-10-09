package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.entity.enumeration.TourType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface TourService extends Service<Tour> {

    void save(String photo, Date date,
              short duration, String description,
              BigDecimal cost, TourType tourType,
              Integer hotelId, Integer countryId);

    void update(Integer id, String photo, Date date,
                short duration, String description, BigDecimal cost,
                TourType tourType, Integer hotelId, Integer countryId);

    List<Review> findReviewsByTour(Integer tourId);
}
