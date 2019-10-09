package by.bsu.travelagency.repository;

import by.bsu.travelagency.config.TestAppConfig;
import by.bsu.travelagency.entity.Country;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class CountryRepositoryTest {
    private static final Integer ADDITIONAL_COUNTRY_ID = 26;
    private static final Integer START_ID = 1;

    @Autowired
    @Qualifier("countryRepository")
    private CountryRepository repository;

    @Test
    public void readAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_COUNTRY_ID - 1, repository.read().size());
    }

    @Test
    public void findById_happyPath() {
        Assert.assertEquals("Portugal", repository.findById(START_ID).getName());
    }

    @Test
    @Transactional
    public void save_happyPath() {
        String countryName = "Spain";
        repository.save(new Country(countryName));
        Assert.assertEquals(countryName, repository.findById(ADDITIONAL_COUNTRY_ID).getName());
    }

    @Test
    @Transactional
    public void update_happyPath() {
        String countryName = "Germany";
        Country country = repository.findById(START_ID);
        country.setName(countryName);
        repository.update(country);
        Assert.assertEquals(countryName, repository.findById(START_ID).getName());
    }

    @Test
    @Transactional
    public void remove_happyPath() {
        repository.remove(START_ID);
        Assert.assertNull(repository.findById(START_ID));
    }

    @Test
    public void findByNonexistentId_notFound() {
        Assert.assertNull(repository.findById(START_ID - 1));
    }
}