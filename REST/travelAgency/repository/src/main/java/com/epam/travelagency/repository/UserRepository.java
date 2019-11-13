package com.epam.travelagency.repository;

import com.epam.travelagency.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User> {

    User getByLogin(String login);

    @Modifying
    @Query(value = "INSERT INTO travel_agency.user_tour (user_id, tour_id) VALUES (?1,?2)",
            nativeQuery = true)
    void addTour(Integer userId, Integer tourId);

    @Query(value = "SELECT COUNT(*) FROM travel_agency.user_tour "
            + "WHERE user_id=? and tour_id=?",
            nativeQuery = true)
    Integer hasTour(Integer userId, Integer tourId);

    @Override
    default Short isArchival(Integer id) {
        return 0;
    }
}
