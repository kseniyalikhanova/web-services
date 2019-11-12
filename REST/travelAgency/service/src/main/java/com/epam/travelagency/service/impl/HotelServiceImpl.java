package com.epam.travelagency.service.impl;

import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.entity.enumeration.Feature;
import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.service.AbstractService;
import com.epam.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class HotelServiceImpl extends AbstractService<Hotel, HotelRepository> implements HotelService {

    @Autowired
    public HotelServiceImpl(HotelRepository repository) {
        super(repository);
    }

    @Override
    public void create(final String name, final short stars,
                     final String website, final double latitude,
                     final double longitude, final List<Feature> features) {
        repository.save(new Hotel(name, stars, website, latitude,
                                    longitude, features));
    }

    @Override
    public void update(final Integer id, final String name, final short stars,
                       final String website, final double latitude,
                       final double longitude, final List<Feature> features) {
        Hotel hotel = getById(id);
        hotel.setName(name);
        hotel.setStars(stars);
        hotel.setWebsite(website);
        hotel.setLatitude(latitude);
        hotel.setLongitude(longitude);
        hotel.setFeatures(features);
        repository.save(hotel);
    }
}
