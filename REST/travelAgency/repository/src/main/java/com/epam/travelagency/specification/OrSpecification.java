package com.epam.travelagency.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrSpecification<T> implements Specification<T> {
    private Specification<T> first;
    private Specification<T> second;

    OrSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder builder) {
        return builder.or(
                first.toPredicate(root, builder),
                second.toPredicate(root, builder)
        );
    }
}
