package com.epam.travelagency.specification.impl;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.specification.Specification;
import com.epam.travelagency.util.Operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ToursByTourDuration implements Specification<Tour> {

    private static final String TOUR_DURATION_FIELD = "duration";
    private short lowDuration;
    private short topDuration;
    private Operator operator;

    public ToursByTourDuration(String duration, Operator operator) {
        this.lowDuration = Short.valueOf(duration);
        this.operator = operator;
    }

    public ToursByTourDuration(String lowDuration, String topDuration) {
        this.lowDuration = Short.valueOf(lowDuration);
        this.topDuration = Short.valueOf(topDuration);
        this.operator = Operator.BETWEEN;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        Predicate comparisonResult;
        switch (operator) {
            case EQUALS:
                comparisonResult = builder.equal(root.get(TOUR_DURATION_FIELD), lowDuration);
                break;
            case BETWEEN:
                comparisonResult = builder.between(root.get(TOUR_DURATION_FIELD), lowDuration, topDuration);
                break;
            case LESS_THAN_OR_EQUALS:
                comparisonResult = builder.lessThanOrEqualTo(root.get(TOUR_DURATION_FIELD), lowDuration);
                break;
            case GREATER_THAN_OR_EQUALS:
                comparisonResult = builder.greaterThanOrEqualTo(root.get(TOUR_DURATION_FIELD), lowDuration);
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Operation '%s' isn't comparison.}", operator));
        }
        return comparisonResult;
    }
}
