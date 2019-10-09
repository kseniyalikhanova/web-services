package by.bsu.travelagency.specification.impl;

import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.parser.DecimalParser;
import by.bsu.travelagency.specification.Specification;
import by.bsu.travelagency.util.Operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ToursByTourCost implements Specification<Tour> {

    private static final String TOUR_COST_FIELD = "cost";
    private BigDecimal lowCost;
    private BigDecimal topCost;
    private Operator operator;

    public ToursByTourCost(String cost, Operator operator) {
        this.lowCost = DecimalParser.parseToBigDecimal(cost).setScale(0, RoundingMode.DOWN);
        this.operator = operator;
    }

    public ToursByTourCost(String lowCost, String topCost) {
        this.lowCost = DecimalParser.parseToBigDecimal(lowCost).setScale(0, RoundingMode.DOWN);
        this.topCost = DecimalParser.parseToBigDecimal(topCost).setScale(0, RoundingMode.DOWN);
        this.operator = Operator.BETWEEN;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        Predicate comparisonResult;
        switch (operator) {
            case EQUALS:
                comparisonResult = builder.between(root.get(TOUR_COST_FIELD), lowCost, lowCost.add(BigDecimal.ONE));
                break;
            case BETWEEN:
                comparisonResult = builder.between(root.get(TOUR_COST_FIELD), lowCost, topCost);
                break;
            case LESS_THAN_OR_EQUALS:
                comparisonResult = builder.lessThanOrEqualTo(root.get(TOUR_COST_FIELD), lowCost);
                break;
            case GREATER_THAN_OR_EQUALS:
                comparisonResult = builder.greaterThanOrEqualTo(root.get(TOUR_COST_FIELD), lowCost);
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Operation '%s' isn't comparison.}", operator));
        }
        return comparisonResult;
    }
}
