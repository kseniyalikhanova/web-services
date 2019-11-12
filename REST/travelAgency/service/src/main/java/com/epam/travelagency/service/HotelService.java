package com.epam.travelagency.service;

import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.entity.enumeration.Feature;

import java.util.List;

public interface HotelService extends Service<Hotel> {

    void create(String name, short stars,
              String website, double latitude,
              double longitude, List<Feature> features);

    void update(Integer id, String name, short stars,
                String website, double latitude,
                double longitude, List<Feature> features);
}
