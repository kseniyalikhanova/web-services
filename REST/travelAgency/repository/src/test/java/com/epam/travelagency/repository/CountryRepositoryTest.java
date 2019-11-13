package com.epam.travelagency.repository;

import com.epam.travelagency.config.RepositoryTestConfig;
import com.epam.travelagency.entity.Country;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CountryRepositoryTest {
    private static final Integer ADDITIONAL_COUNTRY_ID = 26;
    private static final Integer START_ID = 1;

    @Autowired
    @Qualifier("countryRepository")
    private CountryRepository repository;

    @Test
    public void getAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_COUNTRY_ID - 1, ((Collection<Country>) repository.findAll()).size());
    }

    @Test
    public void findById_happyPath() {
        Assert.assertEquals("Portugal", repository.findById(START_ID).get().getName());
    }

    @Test
    public void isArchival_True() {
       Assert.assertEquals(Short.valueOf("0"), repository.isArchival(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        String countryName = "Spain";
        repository.save(new Country(countryName));
        Assert.assertEquals(countryName, repository.findById(ADDITIONAL_COUNTRY_ID).get().getName());
    }

    @Test
    @Transactional
    public void delete_happyPath() {
        repository.deleteById(START_ID);
        Assert.assertEquals(Short.valueOf("1"), repository.isArchival(START_ID));
    }

    @Test
    public void findByNonexistentId_notFound() {
        Assert.assertFalse(repository.findById(START_ID - 1).isPresent());
    }
}