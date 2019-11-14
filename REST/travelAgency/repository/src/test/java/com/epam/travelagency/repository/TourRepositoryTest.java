package com.epam.travelagency.repository;

import com.epam.travelagency.config.RepositoryTestConfig;
import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.enumeration.Feature;
import com.epam.travelagency.entity.enumeration.TourType;
import com.epam.travelagency.parser.DateParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourRepositoryTest {
    private static Tour tour;
    private static final Integer ADDITIONAL_TOUR_ID = 1001;
    private static final Integer START_ID = 1;
    @Autowired
    @Qualifier("tourRepository")
    private TourRepository repository;

    @BeforeClass
    public static void setUp() {
        Feature[] features = new Feature[]{Feature.POOL, Feature.GYM, Feature.HEATING};
        Hotel hotel = new Hotel(24, "Schmidt, Hartmann and Barton", (short) 4,
                         "unesco.org", 60.6569706, 23.1385333,
                Arrays.asList(features));
        Country country = new Country(13, "Czech Republic");
        String stringDate = "2019-01-29";
        tour = new Tour("images/2.jpg",
                        new Date(DateParser.parseStringDateToMilliseconds(stringDate)),
                        (short)8, "Lorem ipsum dolor sit amet. "
                        + "Dolores et voluptates repudiandae.", BigDecimal.valueOf(35375.76),
                        TourType.GROUP_HOLIDAYS, hotel, country);
    }

    @Test
    public void findAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_TOUR_ID - 1, ((Collection<Tour>)repository.findAll()).size());
    }

    @Test
    public void findById_happyPath() {
        tour.setId(START_ID);
        Assert.assertEquals(tour, repository.findById(START_ID).get());
    }

    @Test
    public void isArchival_True() {
        Assert.assertEquals(Short.valueOf("0"), repository.isArchival(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        tour.setId(null);
        repository.save(tour);
        tour.setId(ADDITIONAL_TOUR_ID);
        Assert.assertEquals(tour, repository.findById(ADDITIONAL_TOUR_ID).get());
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

    @Test
    public void getToursIdByCountryId_correctListSize() {
        Assert.assertEquals(37, repository.getToursIdByCountryId(
                tour.getCountry().getId()).size());
    }

    @Test
    public void getAllByCountry() {
        Assert.assertEquals(37, repository.getAllByCountry(tour.getCountry()).size());
    }
}
