package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.User;

import java.util.List;

public interface UserService extends Service<User> {

    boolean save(String login, String password);

    void update(Integer id, String login, String password);

    List<Review> findReviewsByUser(Integer userId);

    boolean addTour(Integer userId, Integer tourId);
}
