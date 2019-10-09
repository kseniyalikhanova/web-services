package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.entity.enumeration.Feature;

import java.util.List;

public interface HotelService extends Service<Hotel> {

    void save(String name, short stars,
              String website, double latitude,
              double longitude, List<Feature> features);

    void update(Integer id, String name, short stars,
                String website, double latitude,
                double longitude, List<Feature> features);
}
