package com.epam.travelagency.service;

import com.epam.travelagency.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@org.springframework.stereotype.Service
public abstract class AbstractService<T, R extends Repository<T>> implements Service<T> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public T getById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<T> paginate(int pageId, int total)  {
        return repository.findAll(PageRequest.of(pageId - 1, total, Sort.by("id"))).getContent();
    }
}
