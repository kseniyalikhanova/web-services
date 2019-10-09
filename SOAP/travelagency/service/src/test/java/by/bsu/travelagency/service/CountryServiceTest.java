package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Country;
import by.bsu.travelagency.repository.CountryRepository;
import by.bsu.travelagency.service.impl.CountryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {
    @Mock
    private CountryRepository repository;
    @InjectMocks
    private CountryServiceImpl service;

    @Test
    public void readAll_correctListSize() {
        List answer = mock(List.class);
        when(answer.size()).thenReturn(25);
        when(repository.read()).thenReturn(answer);
        Assert.assertEquals(25, service.read().size());
    }

    @Test
    public void findById_happyPath() {
        when(repository.findById(anyInt())).thenReturn(new Country(1, "Portugal"));
        Assert.assertEquals("Portugal", service.findById(1).getName());
    }

    @Test
    public void save_happyPath() {
        Country country = new Country("Portugal");
        doNothing().when(repository).save(country);
        service.save("Portugal");
        verify(repository).save(country);
    }

    @Test
    public void remove_happyPath() {
        doNothing().when(repository).remove(anyInt());
        service.remove(anyInt());
        verify(repository).remove(anyInt());
    }
}
