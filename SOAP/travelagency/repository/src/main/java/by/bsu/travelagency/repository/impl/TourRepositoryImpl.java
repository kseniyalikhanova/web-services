package by.bsu.travelagency.repository.impl;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.repository.AbstractRepository;
import by.bsu.travelagency.repository.TourRepository;
import by.bsu.travelagency.util.QueryName;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("tourRepository")
public class TourRepositoryImpl extends AbstractRepository<Tour> implements TourRepository {

    public TourRepositoryImpl() {
        super.setClazz(Tour.class);
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> tours = entityManager.createNamedQuery(
                                QueryName.FIND_ALL_TOURS, Tour.class)
                            .getResultList();
        if (tours == null) {
            tours = new LinkedList<>();
        }
        return tours;
    }

    @Override
    public List<Review> findReviewsByTour(final Integer tourId) {
        return entityManager.createNamedQuery(
                QueryName.FIND_REVIEWS_BY_TOUR, Review.class)
                .setParameter("tourId", tourId)
                .getResultList();

    }
}
