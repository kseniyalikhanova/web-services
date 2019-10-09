package by.bsu.travelagency.repository;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.User;

import java.util.List;

public interface UserRepository extends Repository<User> {

    List<Review> findReviewsByUser(final Integer user);

    void addTour(Integer userId, Integer tourId);

    boolean hasTour(Integer userId, Integer tourId);
}
