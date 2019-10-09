package by.bsu.travelagency.specification.impl;

import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.specification.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ToursByCountryId implements Specification<Tour> {

    private Integer countryId;

    public ToursByCountryId(String countryId) {
        this.countryId = Integer.valueOf(countryId);
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder builder) {
        return builder.equal(root.get("countryId"), countryId);
    }
}
