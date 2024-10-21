package by.ita.je.services;

import by.ita.je.models.TV;
import by.ita.je.repository.TVCrudRepository;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TVServiceTest extends TestUtils {

    @Mock
    private TVCrudRepository tvCrudRepository;
    @InjectMocks
    private TVService service;

    @Test
    void findTVByNumber_then_return_notNull() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        testTV.setNumber(2);
        when(
                tvCrudRepository.findById(testTV.getNumber())
        ).thenReturn(Optional.of(testTV));

        TV actual = service.findTVByNumber(testTV.getNumber());

        assertEquals(actual, testTV);

        verify(
                tvCrudRepository, new Times(1)).findById(testTV.getNumber()
        );
    }

    @Test
    void findTVByNumber_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(tvCrudRepository.findById(number)
        ).thenReturn(Optional.empty());

        TV actual = service.findTVByNumber(number);

        assertNull(actual);

        verify(
                tvCrudRepository, new Times(1)).findById(number);
    }

    @Test
    void findTVByNumber_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(
                tvCrudRepository.findById(number)
        ).thenThrow(new DataAccessException("There exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findTVByNumber(number));
        verify(
                tvCrudRepository, new Times(1)).findById(number);
    }

    @Test
    void updateTV_then_return() {
        TV updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());
        when(tvCrudRepository.findById(updateTV.getNumber()))
                .thenReturn(Optional.of(updateTV));
        when(tvCrudRepository.save(updateTV))
                .thenReturn(updateTV);

        TV actual = service.updateTV(updateTV);

        Assertions.assertEquals(actual, updateTV);

        verify(tvCrudRepository, new Times(1)).findById(updateTV.getNumber());
        verify(tvCrudRepository, new Times(1)).save(updateTV);
    }

    @Test
    void updateTV_then_throws_DataAccessException() {
        TV updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());

        when(
                tvCrudRepository.findById(updateTV.getNumber())
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.updateTV(updateTV));

        verify(tvCrudRepository, new Times(1)).findById(updateTV.getNumber());
        verify(tvCrudRepository, new Times(0)).save(updateTV);
    }

    @Test
    void deleteTV_then_return() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        when(tvCrudRepository.findById(testTV.getNumber())
        ).thenReturn(Optional.of(testTV));
        doNothing().when(tvCrudRepository).deleteById(testTV.getNumber());

        TV actual = service.deleteTV(testTV.getNumber());

        Assertions.assertEquals(actual, testTV);

        verify(tvCrudRepository, new Times(1)).findById(testTV.getNumber());
        verify(tvCrudRepository, new Times(1)).deleteById(testTV.getNumber());

    }

    @Test
    void deleteTV_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(tvCrudRepository.findById(number)
        ).thenReturn(Optional.empty());

        TV actual = service.deleteTV(number);

        assertNull(actual);

        verify(tvCrudRepository, new Times(1)).findById(number);
        verify(tvCrudRepository, new Times(0)).deleteById(number);
    }

    @Test
    void deleteTV_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(tvCrudRepository.findById(number)
        ).thenThrow(new DataAccessException("") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteTV(number));

        verify(tvCrudRepository, new Times(1)).findById(number);
        verify(tvCrudRepository, new Times(0)
        ).deleteById(number);
    }

    @Test
    void insertTV_then_return() {
        TV testTV = buildTV("TV", "LG", true, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.parse("2024-08-08T08:08:54+02"));
        testTV.setNumber(6);
        when(tvCrudRepository.save(testTV)
        ).thenReturn(testTV);

        TV actual = service.insertTV();

        Assertions.assertEquals(actual, testTV);

        verify(tvCrudRepository, new Times(1)).save(testTV);
    }

    @Test
    void readALL_then_return() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        TV updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());
        List<TV> listTV = new ArrayList<>(Arrays.asList(testTV, updateTV));

        when(tvCrudRepository.findAll()
        ).thenReturn(listTV);

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, listTV);

        verify(tvCrudRepository, new Times(1)).findAll();
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(tvCrudRepository.findAll()
        ).thenThrow(new DataAccessException("There exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(tvCrudRepository, new Times(1)). findAll();
    }

    @Test
    void deleteAll_then_return_emptyList() {
        service.deleteAll();

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(tvCrudRepository, new Times(1)).deleteAll();
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        Mockito.doThrow(new DataAccessException("There exception") {
                })
                .when(tvCrudRepository).deleteAll();

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteAll());

        verify(tvCrudRepository, new Times(1)).deleteAll();
        ;
    }
}