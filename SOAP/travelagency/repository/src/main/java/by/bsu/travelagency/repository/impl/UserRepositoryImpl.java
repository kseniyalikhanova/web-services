package by.bsu.travelagency.repository.impl;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.repository.AbstractRepository;
import by.bsu.travelagency.repository.UserRepository;
import by.bsu.travelagency.util.QueryName;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        super.setClazz(User.class);
    }

    @Override
    public List<User> read() {
        List<User> users = entityManager.createNamedQuery(
                QueryName.FIND_ALL_USERS, User.class)
                .getResultList();
        if (users == null) {
            users = new LinkedList<>();
        }
        return users;
    }

    @Override
    public List<Review> findReviewsByUser(final Integer userId) {
        return entityManager.createNamedQuery(
                QueryName.FIND_REVIEWS_BY_USER, Review.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void addTour(Integer userId, Integer tourId) {
        entityManager
                .createNativeQuery("INSERT INTO travel_agency.user_tour (user_id, tour_id) VALUES (?,?)")
                .setParameter(1, userId)
                .setParameter(2, tourId)
                .executeUpdate();
    }

    @Override
    public boolean hasTour(Integer userId, Integer tourId) {
        return !entityManager
                    .createNativeQuery("SELECT COUNT(*) FROM travel_agency.user_tour "
                            + "WHERE user_id=? and tour_id=?")
                    .setParameter(1, userId)
                    .setParameter(2, tourId)
                    .getSingleResult()
                .equals(BigInteger.ZERO);
    }
}
