package by.bsu.travelagency.service.impl;

import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.entity.enumeration.Feature;
import by.bsu.travelagency.repository.HotelRepository;
import by.bsu.travelagency.service.AbstractService;
import by.bsu.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class HotelServiceImpl extends AbstractService<Hotel> implements HotelService {

    private final HotelRepository repository;

    @Autowired
    public HotelServiceImpl(HotelRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void save(final String name, final short stars,
                     final String website, final double latitude,
                     final double longitude, final List<Feature> features) {
        repository.save(new Hotel(name, stars, website, latitude,
                longitude, features));
    }

    @Override
    public void update(final Integer id, final String name, final short stars,
                       final String website, final double latitude,
                       final double longitude, final List<Feature> features) {
        repository.update(new Hotel(id, name, stars, website, latitude,
                longitude, features));
    }
}
