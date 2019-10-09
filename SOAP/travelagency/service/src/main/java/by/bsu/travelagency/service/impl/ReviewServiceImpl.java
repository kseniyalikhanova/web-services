package by.bsu.travelagency.service.impl;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.repository.ReviewRepository;
import by.bsu.travelagency.repository.TourRepository;
import by.bsu.travelagency.repository.UserRepository;
import by.bsu.travelagency.service.AbstractService;
import by.bsu.travelagency.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractService<Review> implements ReviewService {

    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository, UserRepository userRepository, TourRepository tourRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public boolean save(final String text,
                        final Integer userId,
                        final Integer tourId) {
        boolean isSaved = true;
        if (userRepository.hasTour(userId, tourId)) {
            repository.save(new Review(text, userRepository.findById(userId),
                                        tourRepository.findById(tourId)));
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public void update(final Integer id, final Date date, final String text,
                       final Integer userId, final Integer tourId) {
        repository.update(new Review(id, date, text,
                            userRepository.findById(userId),
                            tourRepository.findById(tourId)));
    }
}
