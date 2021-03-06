package com.epam.travelagency.repository;

import com.epam.travelagency.entity.Hotel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface HotelRepository extends Repository<Hotel> {
    @Override
    @Query(value = "SELECT is_archival FROM travel_agency.hotel WHERE id=?",
            nativeQuery = true)
    Short isArchival(Integer id);

    @Override
    @Modifying
    @Query(value = "UPDATE travel_agency.hotel SET is_archival=1 WHERE id=?",
            nativeQuery = true)
    void deleteById(Integer id);
}
