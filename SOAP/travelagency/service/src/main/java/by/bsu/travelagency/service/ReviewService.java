package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Review;

import java.sql.Date;

public interface ReviewService extends Service<Review> {

    boolean save(String text, Integer userId, Integer tourId);

    void update(Integer id, Date date, String text,
                Integer userId, Integer tourId);
}
