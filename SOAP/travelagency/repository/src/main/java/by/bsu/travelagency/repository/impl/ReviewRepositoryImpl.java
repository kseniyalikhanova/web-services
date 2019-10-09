package by.bsu.travelagency.repository.impl;

import by.bsu.travelagency.entity.Review;
import by.bsu.travelagency.repository.AbstractRepository;
import by.bsu.travelagency.repository.ReviewRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository("reviewRepository")
public class ReviewRepositoryImpl extends AbstractRepository<Review> implements ReviewRepository {

    public ReviewRepositoryImpl() {
        super.setClazz(Review.class);
    }

    @Override
    public List<Review> read() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = criteriaBuilder.createQuery(Review.class);
        Root<Review> countryRoot = criteriaQuery.from(Review.class);
        criteriaQuery.select(countryRoot);
        List<Review> reviews = entityManager.createQuery(criteriaQuery).getResultList();
        if (reviews == null) {
            reviews = new LinkedList<>();
        }
        return reviews;
    }
}
