package com.epam.travelagency.specification.impl;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.specification.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserByLogin implements Specification<User> {

    private String login;

    public UserByLogin(String login) {
        this.login = login;
    }
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaBuilder builder) {
        return builder.equal(root.get("login"), login);
    }
}
