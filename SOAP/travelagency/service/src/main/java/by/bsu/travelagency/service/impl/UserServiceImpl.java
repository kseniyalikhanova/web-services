package by.bsu.travelagency.service.impl;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.repository.UserRepository;
import by.bsu.travelagency.service.AbstractService;
import by.bsu.travelagency.service.UserService;
import by.bsu.travelagency.specification.impl.UserByLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder,
                           UserRepository repository) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @Override
    public boolean save(final String login, final String password) {
        boolean isSaved = true;
        if (repository.findBySpecification(new UserByLogin(login)) == null) {
            repository.save(new User(login, passwordEncoder.encode(password)));
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public void update(Integer id, final String login, final String password) {
        repository.update(new User(id, login, password));
    }

    @Override
    public List<Review> findReviewsByUser(final Integer userId) {
        return repository.findReviewsByUser(userId);
    }

    @Override
    public boolean addTour(Integer userId, Integer tourId) {
        boolean isSaved = true;
        if (!repository.hasTour(userId, tourId)) {
            repository.addTour(userId, tourId);
        } else {
            isSaved = false;
        }
        return isSaved;
    }
}
