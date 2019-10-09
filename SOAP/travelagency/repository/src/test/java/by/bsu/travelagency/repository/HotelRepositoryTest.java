package by.bsu.travelagency.repository;

import by.bsu.travelagency.config.TestAppConfig;
import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.entity.enumeration.Feature;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
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
    public void readAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_HOTEL_ID - 1, repository.read().size());
    }

    @Test
    public void findById_happyPath() {
        hotel.setId(START_ID);
        Assert.assertEquals(hotel, repository.findById(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        hotel.setId(null);
        repository.save(hotel);
        hotel.setId(ADDITIONAL_HOTEL_ID);
        Assert.assertEquals(hotel.getName(), repository.findById(ADDITIONAL_HOTEL_ID).getName());
    }

    @Test
    @Transactional
    public void update_happyPath() {
        short stars = 4;
        Hotel foundHotel = repository.findById(START_ID);
        foundHotel.setStars(stars);
        repository.update(foundHotel);
        Assert.assertEquals(stars, repository.findById(START_ID).getStars());
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
