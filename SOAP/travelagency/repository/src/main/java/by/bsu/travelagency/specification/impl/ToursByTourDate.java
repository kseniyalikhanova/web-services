package by.bsu.travelagency.specification.impl;

import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.specification.Specification;
import by.bsu.travelagency.util.Operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class ToursByTourDate implements Specification<Tour> {

    private static final String TOUR_DATE_FIELD = "date";
    private Date lowDate;
    private Date topDate;
    private Operator operator;

    public ToursByTourDate(String date, Operator operator) {
        this.lowDate = java.sql.Date.valueOf(date);
        this.operator = operator;
    }

    public ToursByTourDate(String lowDate, String topDate) {
        this.lowDate = java.sql.Date.valueOf(lowDate);
        this.topDate = java.sql.Date.valueOf(topDate);
        this.operator = Operator.BETWEEN;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        Predicate comparisonResult;
        switch (operator) {
            case EQUALS:
                comparisonResult = builder.equal(root.get(TOUR_DATE_FIELD), lowDate);
                break;
            case BETWEEN:
                comparisonResult = builder.between(root.get(TOUR_DATE_FIELD), lowDate, topDate);
                break;
            case LESS_THAN_OR_EQUALS:
                comparisonResult = builder.lessThanOrEqualTo(root.get(TOUR_DATE_FIELD), lowDate);
                break;
            case GREATER_THAN_OR_EQUALS:
                comparisonResult = builder.greaterThanOrEqualTo(root.get(TOUR_DATE_FIELD), lowDate);
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Operation '%s' isn't comparison.}", operator));
        }
        return comparisonResult;
    }
}
