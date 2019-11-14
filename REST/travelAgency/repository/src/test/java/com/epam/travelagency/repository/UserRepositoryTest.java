package com.epam.travelagency.repository;

import com.epam.travelagency.config.RepositoryTestConfig;
import com.epam.travelagency.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
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
public class UserRepositoryTest {
    private static User user;
    private static final Integer ADDITIONAL_USER_ID = 101;
    private static final Integer USER_ID = 2;
    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;

    @BeforeClass
    public static void setUp() {
        user = new User(USER_ID, "User", "$2a$12$qGSj8IF.KVC2/sE58ZFdJuvX.7fjloLCC2.tSaIrIMdlO5PzPX9bC");
    }

    @Test
    public void findAll_correctListSize() {
        Assert.assertEquals(ADDITIONAL_USER_ID - 1, ((Collection<User>) repository.findAll()).size());
    }

    @Test
    public void findById_happyPath() {
        user.setId(USER_ID);
        Assert.assertEquals(user, repository.findById(USER_ID).get());
    }

    @Test
    @Transactional
    public void save_happyPath() {
        User userTest = new User("sliccardi0", "wE81NdGiUG4");
        repository.save(userTest);
        user.setId(ADDITIONAL_USER_ID);
        Assert.assertEquals(userTest, repository.findById(ADDITIONAL_USER_ID).get());
    }

    @Test
    public void findByNonexistentId_notFound() {
        Assert.assertFalse(repository.findById(USER_ID - 2).isPresent());
    }

    @Test
    public void getByLogin_happyPath() {
        user.setId(USER_ID);
        Assert.assertEquals(user, repository.getByLogin(user.getLogin()));
    }

    @Test
    public void hasTour_True() {
        Integer tourId = 241;
        Assert.assertEquals(Integer.valueOf("1"), repository.hasTour(user.getId(), tourId));
    }
}
