package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Hotel;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface HotelRepository extends Repository<Hotel> {
    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.hotel WHERE id=?",
            nativeQuery = true)
    short isArchival(Integer id);

    @Override
    @Query(value = "UPDATE travel_agency.hotel SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);
}
