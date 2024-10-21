package by.ita.je.services;

import by.ita.je.models.Kettle;
import by.ita.je.repository.KettleCrudRepository;
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
class KettleServiceTest extends TestUtils {

    @Mock
    private KettleCrudRepository kettleCrudRepository;

    @InjectMocks
    private KettleService service;

    @Test
    void findKettleByNumber_then_return_notNull() {
        Kettle testKettle = buildKettle("glass", "notColor", false, false, BigDecimal.valueOf(80.5), 'B', ZonedDateTime.parse("2023-12-31T14:11:54+02"));
        testKettle.setNumber(2);
        Integer number = 2;
        Mockito.when(kettleCrudRepository.findById(number)
        ).thenReturn(Optional.of(testKettle));

        Kettle actual = service.findKettleByNumber(number);

        assertEquals(actual, testKettle);

        Mockito.verify(
                kettleCrudRepository, new Times(1)).findById(number);
    }

    @Test
    void findKettleByNumber_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        Mockito.when(kettleCrudRepository.findById(number)
        ).thenReturn(Optional.empty());

        Kettle actual = service.findKettleByNumber(number);

        assertNull(actual);

        Mockito.verify(
                kettleCrudRepository, new Times(1)).findById(number);
    }

    @Test
    void findKettleByNumber_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        Mockito.when(kettleCrudRepository.findById(number)
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findKettleByNumber(number));

        Mockito.verify(
                kettleCrudRepository, new Times(1)).findById(number);
    }

    @Test
    void updateKettle_then_return() {
        Kettle updateKettle = buildKettle("glass", "pink", false, false, BigDecimal.valueOf(122.22), 'B', ZonedDateTime.parse("2023-12-31T14:11:54+02"));

        Mockito.when(kettleCrudRepository.findById(updateKettle.getNumber())
        ).thenReturn(Optional.of(updateKettle));

        Mockito.when(kettleCrudRepository.save(updateKettle)
        ).thenReturn(updateKettle);

        Kettle actual = service.updateKettle(updateKettle);

        Assertions.assertEquals(actual, updateKettle);
        verify(kettleCrudRepository, new Times(1)).save(updateKettle);
    }

    @Test
    void deleteKettle_then_return() {
        Kettle testKettle = buildKettle("glass", "notColor", false, false, BigDecimal.valueOf(80.5), 'B', ZonedDateTime.parse("2023-12-31T14:11:54+02"));
        testKettle.setNumber(2);

        Mockito.when(kettleCrudRepository.findById(testKettle.getNumber())
        ).thenReturn(Optional.of(testKettle));
        Kettle actual = service.deleteKettle(testKettle.getNumber());

        Assertions.assertEquals(actual, testKettle);

        Mockito.verify(kettleCrudRepository, new Times(1)).deleteById(2);
    }

    @Test
    void deleteKettle_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(kettleCrudRepository.findById(number)
        ).thenReturn(Optional.empty());

        Kettle actual = service.deleteKettle(number);

        assertNull(actual);
        Mockito.verify(kettleCrudRepository, new Times(0)).deleteById(number);
    }

    @Test
    void insertKettle_then_return() {
        Kettle testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        when(kettleCrudRepository.save(testKettle)
        ).thenReturn(testKettle);
        Kettle actual = service.insertKettle(testKettle);

        Assertions.assertEquals(actual, testKettle);

        verify(kettleCrudRepository, new Times(1)).save(testKettle);
    }

    @Test
    void insertKettle_then_trows_DataAccessException() {
        Kettle testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        when(kettleCrudRepository.save(testKettle)
        ).thenThrow(new DataAccessException("") {
        });


        Assertions.assertThrows(DataAccessException.class, () -> service.insertKettle(testKettle));

        verify(kettleCrudRepository, new Times(1)).save(testKettle);
    }

    @Test
    void readALL_then_return() {
        Kettle testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));
        Kettle updateKettle = buildKettle("glass", "pink", true, true, BigDecimal.valueOf(122.22), 'A', ZonedDateTime.parse("2011-11-11T11:11:11+02"));
        List<Kettle> listKettle = new ArrayList<>(Arrays.asList(updateKettle, testKettle));

        when(kettleCrudRepository.findAll()
        ).thenReturn(listKettle);

        List<Kettle> actualList = service.readALL();

        Assertions.assertEquals(actualList, listKettle);
        verify(kettleCrudRepository, new Times(1)).findAll();
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(kettleCrudRepository.findAll()).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(kettleCrudRepository, new Times(1)).findAll();
    }

    @Test
    void deleteAll_then_emptyList() {
        Mockito.doNothing().when(kettleCrudRepository).deleteAll();
        service.deleteAll();
        List<Kettle> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        Mockito.verify(kettleCrudRepository, new Times(1)).deleteAll();
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        Mockito.doThrow(new DataAccessException("") {
                })
                .when(kettleCrudRepository).deleteAll();

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteAll());

        Mockito.verify(kettleCrudRepository, new Times(1)).deleteAll();
    }
}