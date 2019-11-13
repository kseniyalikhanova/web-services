package com.epam.travelagency.service.impl;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.service.AbstractService;
import com.epam.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends AbstractService<User, UserRepository> implements UserService {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder,
                           UserRepository repository) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean create(final String login, final String password) {
        boolean isSaved = true;
        if (repository.getByLogin(login) == null) {
            repository.save(new User(login, passwordEncoder.encode(password)));
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public void update(Integer id, final String login, final String password) {
        User user = getById(id);
        user.setLogin(login);
        user.setPassword(password);
        repository.save(user);
    }

    @Override
    public boolean addTour(Integer userId, Integer tourId) {
        boolean isSaved = true;
        if (!hasTour(userId, tourId)) {
            repository.addTour(userId, tourId);
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public boolean hasTour(Integer userId, Integer tourId) {
        return !repository.hasTour(userId, tourId).equals(0);
    }

    @Override
    public User getByLogin(String login) {
        return repository.getByLogin(login);
    }

    @Override
    public List<Tour> getTours(Integer userId) {
        return getById(userId).getTours();
    }
}
