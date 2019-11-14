package com.epam.travelagency.repository;

import com.epam.travelagency.config.RepositoryTestConfig;
import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.entity.enumeration.Feature;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HotelRepositoryTest {
    private static Hotel hotel;
    private static final Integer ADDITIONAL_HOTEL_ID = 101;
    private static final Integer START_ID = 1;
    @Autowired
    @Qualifier("hotelRepository")
    private HotelRepository repository;

    @BeforeClass
    public static void setUp() {
        Feature[] features = new Feature[]{Feature.WIFI, Feature.HAIR_DRYER, Feature.TV};
        hotel = new Hotel("Wiegand-Herzog", (short) 5,
                          "foxnews.com", 7.198851, 5.593239,
                          Arrays.asList(features));
    }

    @Test
    public void getAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_HOTEL_ID - 1, ((Collection<Hotel>)repository.findAll()).size());
    }

    @Test
    public void findById_happyPath() {
        hotel.setId(START_ID);
        Assert.assertEquals(hotel, repository.findById(START_ID).get());
    }

    @Test
    public void isArchival_True() {
        Assert.assertEquals(Short.valueOf("0"), repository.isArchival(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        hotel.setId(null);
        repository.save(hotel);
        hotel.setId(ADDITIONAL_HOTEL_ID);
        Assert.assertEquals(hotel, repository.findById(ADDITIONAL_HOTEL_ID).get());
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
