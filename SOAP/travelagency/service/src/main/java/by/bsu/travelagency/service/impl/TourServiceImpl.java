package by.bsu.travelagency.service.impl;

import by.bsu.travelagency.entity.Country;
import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.entity.enumeration.TourType;
import by.bsu.travelagency.repository.TourRepository;
import by.bsu.travelagency.service.AbstractService;
import by.bsu.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class TourServiceImpl extends AbstractService<Tour> implements TourService {

    private final TourRepository repository;

    @Autowired
    public TourServiceImpl(TourRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void save(final String photo, final Date date,
                     final short duration, final String description,
                     final BigDecimal cost, final TourType tourType,
                     final Integer hotelId, final Integer countryId) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        Country country = new Country();
        country.setId(countryId);
        repository.save(new  Tour(photo, date, duration,
                                  description, cost, tourType,
                                  hotel, country));
    }

    @Override
    public void update(final Integer id, final String photo, final Date date,
                          final short duration, final String description, final BigDecimal cost,
                          final TourType tourType, final Integer hotelId, final Integer countryId) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        Country country = new Country();
        country.setId(countryId);
        repository.update(new  Tour(id, photo, date,
                                   duration, description, cost,
                                   tourType, hotel, country));
    }

    @Override
    public List<Review> findReviewsByTour(final Integer tourId) {
        return repository.findReviewsByTour(tourId);
    }
}
