package com.epam.travelagency.service.impl;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.enumeration.TourType;
import com.epam.travelagency.repository.TourRepository;
import com.epam.travelagency.service.AbstractService;
import com.epam.travelagency.service.CountryService;
import com.epam.travelagency.service.HotelService;
import com.epam.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;

@Service
@Transactional
public class TourServiceImpl extends AbstractService<Tour, TourRepository> implements TourService {

    private final CountryService countryService;
    private final HotelService hotelService;

    @Autowired
    public TourServiceImpl(TourRepository repository, CountryService countryService, HotelService hotelService) {
        super(repository);
        this.countryService = countryService;
        this.hotelService = hotelService;
    }

    @Override
    public void create(final String photo, final Date date,
                     final short duration, final String description,
                     final BigDecimal cost, final TourType tourType,
                     final Integer hotelId, final Integer countryId) {
        repository.save(new Tour(photo, date, duration,
                                        description, cost, tourType,
                                        hotelService.getById(hotelId),
                                        countryService.getById(countryId)));
    }

    @Override
    public void update(final Integer id, final String photo, final Date date,
                       final short duration, final String description,
                       final BigDecimal cost, final Integer hotelId) {
        Tour tour = getById(id);
        tour.setPhoto(photo);
        tour.setDate(date);
        tour.setDuration(duration);
        tour.setDescription(description);
        tour.setCost(cost);
        tour.setHotel(hotelService.getById(hotelId));
        repository.save(tour);
    }
}
