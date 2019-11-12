package com.epam.travelagency.service.impl;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.repository.ReviewRepository;
import com.epam.travelagency.service.AbstractService;
import com.epam.travelagency.service.ReviewService;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractService<Review, ReviewRepository> implements ReviewService {

    private final UserService userService;
    private final TourService tourService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository, UserService userService, TourService tourService) {
        super(repository);
        this.userService = userService;
        this.tourService = tourService;
    }

    @Override
    public boolean create(final String text,
                        final Integer userId,
                        final Integer tourId) {
        boolean isSaved = true;
        if (userService.hasTour(userId, tourId)) {
            repository.save(new Review(text, userService.getById(userId),
                                        tourService.getById(tourId)));
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public void update(final Integer id, final Date date, final String text,
                       final Integer userId, final Integer tourId) {
        Review review = getById(id);
        review.setDate(date);
        review.setText(text);
        review.setUser(userService.getById(userId));
        review.setTour(tourService.getById(tourId));
        repository.save(review);
    }

    @Override
    public List<Review> getByTourId(Integer tourId) {
        return repository.getAllByTour(tourService.getById(tourId));
    }

    @Override
    public List<Review> getByUserId(Integer userId) {
        return repository.getAllByUser(userService.getById(userId));
    }
}
