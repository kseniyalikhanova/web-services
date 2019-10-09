package by.bsu.travelagency.repository;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.entity.Tour;

import java.util.List;

public interface TourRepository extends Repository<Tour> {

        List<Review> findReviewsByTour(final Integer tour);
}
