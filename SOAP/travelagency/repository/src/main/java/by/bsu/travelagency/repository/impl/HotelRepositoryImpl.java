package by.bsu.travelagency.repository.impl;

import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.repository.AbstractRepository;
import by.bsu.travelagency.repository.HotelRepository;
import by.bsu.travelagency.util.QueryName;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("hotelRepository")
public class HotelRepositoryImpl extends AbstractRepository<Hotel> implements HotelRepository {

    public HotelRepositoryImpl() {
        super.setClazz(Hotel.class);
    }

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = entityManager.createNamedQuery(
                                QueryName.FIND_ALL_HOTELS, Hotel.class)
                            .getResultList();
        if (hotels == null) {
            hotels = new LinkedList<>();
        }
        return hotels;
    }
}
