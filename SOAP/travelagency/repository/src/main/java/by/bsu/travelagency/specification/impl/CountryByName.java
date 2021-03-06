package by.bsu.travelagency.specification.impl;

import by.bsu.travelagency.entity.Country;
import by.bsu.travelagency.specification.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CountryByName implements Specification<Country> {

    private String name;

    public CountryByName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Country> root, CriteriaBuilder builder) {
        return builder.equal(root.get("name"), name);
    }
}
