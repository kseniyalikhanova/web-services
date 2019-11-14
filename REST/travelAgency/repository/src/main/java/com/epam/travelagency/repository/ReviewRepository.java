package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface ReviewRepository extends Repository<Review> {

    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.review WHERE id=?",
            nativeQuery = true)
    Short isArchival(Integer id);

    @Override
    @Modifying
    @Query(value = "UPDATE travel_agency.review SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);
}
