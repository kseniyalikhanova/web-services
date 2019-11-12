package com.epam.travelagency.service;

import com.epam.travelagency.entity.Review;

import java.sql.Date;
import java.util.List;

public interface ReviewService extends Service<Review> {

    boolean create(String text, Integer userId, Integer tourId);

    void update(Integer id, Date date, String text,
                Integer userId, Integer tourId);

    List<Review> getByTourId(Integer tourId);

    List<Review> getByUserId(Integer userId);
}
