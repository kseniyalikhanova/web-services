package com.epam.travelagency.service;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;

import java.util.List;

public interface UserService extends Service<User> {

    boolean create(String login, String password);

    void update(Integer id, String login, String password);

    boolean addTour(Integer userId, Integer tourId);

    boolean hasTour(Integer userId, Integer tourId);

    User getByLogin(String login);

    List<Tour> getUserTours(Integer userId);
}
