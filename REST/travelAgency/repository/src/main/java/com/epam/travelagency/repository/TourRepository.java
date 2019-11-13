package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Tour;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface TourRepository extends Repository<Tour> {
    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.tour WHERE id=?",
            nativeQuery = true)
    Short isArchival(Integer id);

    @Override
    @Query(value = "UPDATE travel_agency.tour SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);
}
