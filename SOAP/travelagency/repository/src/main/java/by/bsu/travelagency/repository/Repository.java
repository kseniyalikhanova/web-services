package by.bsu.travelagency.repository;

import by.bsu.travelagency.specification.Specification;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    void save(T model);

    void update(T model);

    void remove(Integer id);

    T findById(Integer id);

    List<T> findBySpecification(Specification<T> specification);

    List<T> paginate(int pageId, int total);

    Long getCountOfEntity();
}
