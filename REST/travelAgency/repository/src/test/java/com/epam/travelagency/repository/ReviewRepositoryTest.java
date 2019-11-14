package com.epam.travelagency.repository;

import com.epam.travelagency.config.RepositoryTestConfig;
import com.epam.travelagency.entity.*;
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
public class ReviewRepositoryTest {
    private static Review review;
    private static final Integer ADDITIONAL_REVIEW_ID = 1001;
    private static final Integer START_ID = 1;
    @Autowired
    @Qualifier("reviewRepository")
    private ReviewRepository repository;

    @BeforeClass
    public static void setUp() {
        User user = new User(49, "qwerty46", "$2a$12$jB46H.BzUsWThR.AJ71DnuBqU/XSWQJ1bLLJu79DhtJwO07nXhGLO");
        Feature[] features = new Feature[]{Feature.POOL, Feature.GYM, Feature.HEATING};
        Hotel hotel = new Hotel(
                10, "Miller-Feil", (short) 4,
                "xing.com", 13.8266134, 122.9580541,
                Arrays.asList(features));
        Country country = new Country(7, "Russia");
        String stringTourDate = "2019-01-30";
        Tour tour = new Tour(
                12, "images/2.jpg",
                new Date(DateParser.parseStringDateToMilliseconds(stringTourDate)),
                (short)1, "Lorem ipsum dolor sit amet. "
                + "Dolores et voluptates repudiandae.", BigDecimal.valueOf(65996.87),
                TourType.AFRICAN_SAFARI_HOLIDAYS, hotel, country);
        String stringDate = "2018-08-05";
        review = new Review(new Date(DateParser.parseStringDateToMilliseconds(stringDate)),
                "Lorem ipsum dolor sit amet. Obcaecati cupiditate non rec.",
                user, tour);
    }

    @Test
    public void findAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_REVIEW_ID - 1, ((Collection<Review>) repository.findAll()).size());
    }

    @Test
    public void findById_happyPath() {
        review.setId(START_ID);
        Assert.assertEquals(review, repository.findById(START_ID).get());
    }

    @Test
    public void isArchival_True() {
        Assert.assertEquals(Short.valueOf("0"), repository.isArchival(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        review.setId(null);
        review.setDate(new Date(new java.util.Date().getTime()));
        repository.save(review);
        review.setId(ADDITIONAL_REVIEW_ID);
        Assert.assertEquals(review, repository.findById(ADDITIONAL_REVIEW_ID).get());
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

