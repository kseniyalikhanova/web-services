package com.epam.travelagency.specification.impl;

import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.specification.Specification;

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
