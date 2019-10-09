package by.bsu.travelagency.service;

import by.bsu.travelagency.specification.Specification;

import java.util.List;

public interface Service<T> {

    List<T> read();

    void remove(final Integer id);

    T findById(final Integer id);

    List<T> findBySpecification(Specification<T> specification);

    List<T> paginate(int pageId, int total);

    Long getCountOfEntity();
}
