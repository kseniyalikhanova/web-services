package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ReviewRepository extends Repository<Review> {

    List<Review> getAllByTour(Tour tour);

    List<Review> getAllByUser(User user);
}
