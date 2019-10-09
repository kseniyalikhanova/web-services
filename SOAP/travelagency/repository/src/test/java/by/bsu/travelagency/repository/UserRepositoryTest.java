package by.bsu.travelagency.repository;

import by.bsu.travelagency.config.TestAppConfig;
import by.bsu.travelagency.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class UserRepositoryTest {
    private static User user;
    private static final Integer ADDITIONAL_USER_ID = 101;
    private static final Integer START_ID = 1;
    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;

    @BeforeClass
    public static void setUp() {
        user = new User("sliccardi0", "wE81NdGiUG4");
    }

    @Test
    public void findAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_USER_ID - 1, repository.findAll().size());
    }

    @Test
    public void findById_happyPath() {
        user.setId(START_ID);
        Assert.assertEquals(user, repository.findById(START_ID));
    }

    @Test
    @Transactional
    public void save_happyPath() {
        user.setId(null);
        repository.save(user);
        user.setId(ADDITIONAL_USER_ID);
        Assert.assertEquals(user, repository.findById(ADDITIONAL_USER_ID));
    }

    @Test
    @Transactional
    public void update_happyPath() {
        String password = "Password";
        User foundUser = repository.findById(START_ID);
        foundUser.setPassword(password);
        repository.update(foundUser);
        Assert.assertEquals(password,repository.findById(START_ID)
                .getPassword());
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

    @Test
    public void findReviews_correctListSize() {
        Assert.assertEquals(15, repository.findReviewsByUser(49).size());
    }
}
