package by.bsu.travelagency.repository;

import by.bsu.travelagency.specification.Specification;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Getter
@Setter
@org.springframework.stereotype.Repository
public abstract class AbstractRepository<T> implements Repository<T> {

    private Class<T> clazz;
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void save(final T model) {
        entityManager.persist(model);
    }

    @Override
    public void update(final T model) {
        entityManager.merge(model);
    }

    @Override
    public void remove(final Integer id) {
        entityManager.remove(findById(id));
    }

    @Override
    public T findById(Integer id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<T> findBySpecification(Specification<T> specification){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        Predicate predicate = specification.toPredicate(root, criteriaBuilder);
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<T> paginate(int pageId, int total){

        int firstEntityId = (pageId - 1) * total + 1;

        Query query = entityManager.createQuery("SELECT t FROM " + clazz.getName() + " t");
        query.setFirstResult(firstEntityId);
        query.setMaxResults(total);
        return (List<T>) query.getResultList();

    }

    @Override
    public Long getCountOfEntity(){
        return (Long) entityManager
                        .createQuery("SELECT COUNT(t) FROM " + clazz.getName() + " t")
                        .getSingleResult();
    }
}
