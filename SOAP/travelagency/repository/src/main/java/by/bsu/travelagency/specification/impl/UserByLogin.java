package by.bsu.travelagency.specification.impl;

import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.specification.Specification;

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
