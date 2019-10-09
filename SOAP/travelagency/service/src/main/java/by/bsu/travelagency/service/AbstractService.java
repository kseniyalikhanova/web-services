package by.bsu.travelagency.service;

import by.bsu.travelagency.repository.Repository;
import by.bsu.travelagency.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public abstract class AbstractService<T> implements Service<T> {

    private final Repository<T> repository;

    @Autowired
    public AbstractService(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> read() {
        return repository.read();
    }

    @Override
    public void remove(Integer id) {
        repository.remove(id);
    }

    @Override
    public T findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findBySpecification(Specification<T> specification) {
        return repository.findBySpecification(specification);
    }

    @Override
    public List<T> paginate(int pageId, int total)  {
        return repository.paginate(pageId, total);
    }

    @Override
    public Long getCountOfEntity() {
        return repository.getCountOfEntity();
    }
}
