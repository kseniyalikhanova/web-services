package com.epam.travelagency.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    boolean delete(final Integer id);

    T getById(final Integer id);

    List<T> paginate(int pageId, int total);
}
