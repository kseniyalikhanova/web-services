package by.bsu.travelagency.service;

import by.bsu.travelagency.entity.Hotel;
import by.bsu.travelagency.repository.HotelRepository;
import by.bsu.travelagency.service.impl.HotelServiceImpl;
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
public class HotelServiceTest {
    @Mock
    private HotelRepository repository;
    @InjectMocks
    private HotelServiceImpl service;

    @Test
    public void findAll_correctListSize() {
        List answer = mock(List.class);
        when(answer.size()).thenReturn(100);
        when(repository.findAll()).thenReturn(answer);
        Assert.assertEquals(100, service.findAll().size());
    }

    @Test
    public void findById_happyPath() {
        when(repository.findById(anyInt())).thenReturn(new Hotel());
        service.findById(anyInt());
        verify(repository).findById(anyInt());
    }

    @Test
    public void save_happyPath() {
        doNothing().when(repository).save(new Hotel());
        service.save(null, (short) 0, null, 0.0,0.0,null);
        verify(repository).save(new Hotel());

    }

    @Test
    public void update_happyPath() {
        doNothing().when(repository).update(new Hotel());
        service.update(null,null, (short) 0, null, 0.0,0.0,null);
        verify(repository).update(new Hotel());
    }

    @Test
    public void remove_happyPath() {
        doNothing().when(repository).remove(anyInt());
        service.remove(anyInt());
        verify(repository).remove(anyInt());
    }
}
