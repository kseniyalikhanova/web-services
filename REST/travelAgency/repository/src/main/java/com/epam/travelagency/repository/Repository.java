package com.epam.travelagency.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface Repository<T> extends PagingAndSortingRepository<T, Integer> {
    Short isArchival(Integer id);
}
