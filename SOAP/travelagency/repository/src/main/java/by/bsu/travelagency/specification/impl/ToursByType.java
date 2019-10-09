package by.bsu.travelagency.specification.impl;

import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.entity.enumeration.TourType;
import by.bsu.travelagency.specification.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ToursByType implements Specification<Tour> {
    private static final String UNDERSCORE = "_";
    private static final String SPACE = " ";
    private TourType type;

    public ToursByType(String tourType) {
        this.type = TourType.valueOf(tourType.replaceAll(SPACE, UNDERSCORE)
                                             .toUpperCase());
    }
    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        return builder.equal(root.get("tourType"), type);
    }
}
