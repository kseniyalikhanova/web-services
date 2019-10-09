package by.bsu.travelagency.repository;

import by.bsu.travelagency.config.TestAppConfig;
import by.bsu.travelagency.entity.Country;
import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.entity.Tour;
import by.bsu.travelagency.entity.enumeration.Feature;
import by.bsu.travelagency.entity.enumeration.TourType;
import by.bsu.travelagency.parser.DateParser;
import by.bsu.travelagency.specification.Specification;
import by.bsu.travelagency.specification.impl.ToursByTourCost;
import by.bsu.travelagency.specification.impl.ToursByTourDate;
import by.bsu.travelagency.specification.impl.ToursByTourDuration;
import by.bsu.travelagency.specification.impl.ToursByType;
import by.bsu.travelagency.util.Operator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class TourRepositoryTest {
    private static Tour tour;
    private static final Integer ADDITIONAL_TOUR_ID = 1001;
    private static final Integer START_ID = 1;
    private Specification<Tour> specification;
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
        Assert.assertEquals(ADDITIONAL_TOUR_ID - 1, repository.findAll().size());
    }

    @Test
    public void findById_happyPath() {
        tour.setId(START_ID);
        Assert.assertEquals(tour, repository.findById(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        tour.setId(null);
        repository.save(tour);
        tour.setId(ADDITIONAL_TOUR_ID);
        Assert.assertEquals(tour, repository.findById(ADDITIONAL_TOUR_ID));
    }

    @Test
    @Transactional
    public void findByDate_correctListSize() {
        specification = new ToursByTourDate("2020-04-12", Operator.EQUALS);
        Assert.assertEquals(5, repository.findBySpecification(specification).size());
    }

    @Test
    public void findByDuration_correctListSize() {
        specification = new ToursByTourDuration("4", Operator.EQUALS);
        Assert.assertEquals(29, repository.findBySpecification(specification).size());
    }

    @Test
    public void findByType_correctListSize() {
        specification = new ToursByType("Wildlife and nature holidays");
        Assert.assertEquals(103, repository.findBySpecification(specification).size());
    }

    @Test
    public void findByCost_correctListSize() {
        specification = new ToursByTourCost("97507.14", Operator.EQUALS);
        Assert.assertEquals(1, repository.findBySpecification(specification).size());
    }

    @Test
    public void findByDateAndDuration_correctListSize() {
        specification = new ToursByTourDate("2020-11-22", Operator.EQUALS)
                            .and(new ToursByTourDuration("19", Operator.EQUALS));
        Assert.assertEquals(2, repository.findBySpecification(specification).size());
    }
}
