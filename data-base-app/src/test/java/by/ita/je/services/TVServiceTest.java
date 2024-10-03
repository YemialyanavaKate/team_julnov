package by.ita.je.services;

import by.ita.je.models.TV;
import by.ita.je.repository.TVJdbcTemp;
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
    private TVJdbcTemp tvJdbcTemp;
    @InjectMocks
    private TVService service;

    @Test
    void findTVByNumber_then_return_notNull() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        when(
                tvJdbcTemp.findTVByNumber(testTV.getNumber())
        ).thenReturn(testTV);

        TV actual = service.findTVByNumber(testTV.getNumber());

        assertEquals(actual, testTV);

        verify(
                tvJdbcTemp, new Times(1)).findTVByNumber(testTV.getNumber()
        );
    }

    @Test
    void findTVByNumber_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(
                tvJdbcTemp.findTVByNumber(number)
        ).thenReturn(null);

        TV actual = service.findTVByNumber(number);

        assertNull(actual);

        verify(
                tvJdbcTemp, new Times(1)).findTVByNumber(number);
    }

    @Test
    void findTVByNumber_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(
                tvJdbcTemp.findTVByNumber(number)
        ).thenThrow(new DataAccessException("There exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findTVByNumber(number));
        verify(
                tvJdbcTemp, new Times(1)).findTVByNumber(number);
    }

    @Test
    void updateTV_then_return() {
        TV updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());

        when(
                tvJdbcTemp.updateTV(updateTV
                )).thenReturn(updateTV);

        TV actual = service.updateTV(updateTV);

        Assertions.assertEquals(actual, updateTV);

        verify(tvJdbcTemp, new Times(1)).updateTV(updateTV);
    }

    @Test
    void updateTV_then_throws_DataAccessException() {
        TV updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());

        when(
                tvJdbcTemp.updateTV(updateTV)
        ).thenThrow(new DataAccessException("There is exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.updateTV(updateTV));

        verify(tvJdbcTemp, new Times(1)).updateTV(updateTV);
        verify(tvJdbcTemp, new Times(0)).findTVByNumber(updateTV.getNumber());
    }

    @Test
    void deleteTV_then_return() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        when(tvJdbcTemp.deleteTV(testTV.getNumber())
        ).thenReturn(testTV);

        TV actual = service.deleteTV(testTV.getNumber());

        Assertions.assertEquals(actual, testTV);

        verify(tvJdbcTemp, new Times(1)).deleteTV(testTV.getNumber());
    }

    @Test
    void deleteTV_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(tvJdbcTemp.deleteTV(number)
        ).thenReturn(null);

        TV actual = service.deleteTV(number);

        assertNull(actual);

        verify(tvJdbcTemp, new Times(1)).deleteTV(number);
    }

    @Test
    void deleteTV_then_trows_DataAccessException() {
        //testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(tvJdbcTemp.deleteTV(number)
        ).thenThrow(new DataAccessException("There is exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteTV(number));

        verify(tvJdbcTemp, new Times(1)
        ).deleteTV(number);
    }

    @Test
    void insertTV_then_return() {
        TV testTV = buildTV("TV", "LG", true, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.parse("2024-08-08T08:08:54+02"));
        testTV.setNumber(6);
        when(tvJdbcTemp.insertTV(testTV)
        ).thenReturn(testTV);

        TV actual = service.insertTV();

        Assertions.assertEquals(actual, testTV);

        verify(tvJdbcTemp, new Times(1)).insertTV(testTV);
    }

    @Test
    void insertTV_then_trows_DataAccessException() {
        TV testTV = buildTV("TV", "LG", true, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.parse("2024-08-08T08:08:54+02"));
        testTV.setNumber(6);
        doThrow(new DataAccessException("") {
        }).when(tvJdbcTemp).insertTV(testTV);

        Assertions.assertThrows(DataAccessException.class, () -> service.insertTV());

        verify(tvJdbcTemp, new Times(1)
        ).insertTV(testTV);
    }

    @Test
    void readALL_then_return() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        TV updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());
        List<TV> listTV = new ArrayList<>(Arrays.asList(testTV, updateTV));

        when(tvJdbcTemp.readALL()
        ).thenReturn(listTV);

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, listTV);

        verify(tvJdbcTemp, new Times(1)).readALL();
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(tvJdbcTemp.readALL()
        ).thenThrow(new DataAccessException("There exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(tvJdbcTemp, new Times(1)).readALL();
    }

    @Test
    void deleteAll_then_return_emptyList() {
        service.deleteAll();

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(tvJdbcTemp, new Times(1)).deleteAll();
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        Mockito.doThrow(new DataAccessException("There exception") {
                })
                .when(tvJdbcTemp).deleteAll();

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteAll());

        verify(tvJdbcTemp, new Times(1)).deleteAll();
        ;
    }
}