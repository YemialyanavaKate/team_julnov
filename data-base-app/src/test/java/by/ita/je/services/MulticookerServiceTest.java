package by.ita.je.services;

import by.ita.je.models.Multicooker;
import by.ita.je.repository.MulticookerRepoEM;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MulticookerServiceTest extends TestUtils {

    @Mock
    private MulticookerRepoEM multicookerRepoEM;
    @InjectMocks
    private MulticookerService service;

    @Test
    void findMulticookerByNumber_then_return() {
        Multicooker testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        Integer number = 2;
        when(multicookerRepoEM.findMulticookerByNumber(number)
        ).thenReturn(testMulticooker);

        Multicooker actual = service.findMulticookerByNumber(number);

        assertEquals(actual, testMulticooker);

        verify(
                multicookerRepoEM, new Times(1)).findMulticookerByNumber(number);
    }

    @Test
    void findMulticookerByNumber_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(multicookerRepoEM.findMulticookerByNumber(number)
        ).thenReturn(null);

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNull(actual);

        verify(
                multicookerRepoEM, new Times(1)).findMulticookerByNumber(number);
    }

    @Test
    void findMulticookerByNumber_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(multicookerRepoEM.findMulticookerByNumber(number))
                .thenThrow(new DataAccessException("There exception") {
                });

        Assertions.assertThrows(DataAccessException.class, () -> service.findMulticookerByNumber(number));
        verify(multicookerRepoEM, new Times(1)).findMulticookerByNumber(number);
    }

    @Test
    void updateMulticooker_then_return() {
        Multicooker updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());

        when(multicookerRepoEM.updateMulticooker(updateMulticooker)).thenReturn(updateMulticooker);

        Multicooker actual = service.updateMulticooker(updateMulticooker);

        Assertions.assertEquals(actual, updateMulticooker);
        verify(multicookerRepoEM, new Times(1)).updateMulticooker(updateMulticooker);
    }

    @Test
    void updateMulticooker_then_throws_DataAccessException() {
        Multicooker updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());

        when(multicookerRepoEM.updateMulticooker(updateMulticooker)).thenThrow(new DataAccessException("There is exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.updateMulticooker(updateMulticooker));

        verify(multicookerRepoEM, new Times(1)).updateMulticooker(updateMulticooker);
    }

    @Test
    void deleteMulticooker_then_return() {
        Multicooker testMulticooker = buildMulticooker("NonRemovablePanels", "for sweet", false, 20001, BigDecimal.valueOf(99.8), 'A', ZonedDateTime.parse("2024-05-11T23:23:54+02"));
        Integer number = 2;

        when(multicookerRepoEM.deleteMulticooker(number))
                .thenReturn(testMulticooker);

        Multicooker actual = service.deleteMulticooker(number);

        Assertions.assertEquals(actual, testMulticooker);

        verify(multicookerRepoEM, new Times(1)).deleteMulticooker(number);

    }

    @Test
    void deleteMulticooker_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(multicookerRepoEM.deleteMulticooker(number)
        ).thenReturn(null);

        Multicooker actual = service.deleteMulticooker(number);

        assertNull(actual);

        verify(multicookerRepoEM, new Times(1)
        ).deleteMulticooker(number);
    }

    @Test
    void deleteMulticooker_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(multicookerRepoEM.deleteMulticooker(number))
                .thenThrow(new DataAccessException("There is exception") {
                           }
                );

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteMulticooker(number));

        verify(multicookerRepoEM, new Times(1)
        ).deleteMulticooker(number);
    }

    @Test
    void insertMulticooker_then_return() {
        Multicooker testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(multicookerRepoEM.insertMulticooker(testMulticooker)
        ).thenReturn(testMulticooker);
        when(multicookerRepoEM.insertMulticooker(testMulticooker))
                .thenReturn(testMulticooker);

        Multicooker actual = service.insertMulticooker(testMulticooker);

        Assertions.assertEquals(actual, testMulticooker);

        verify(multicookerRepoEM, new Times(1)
        ).insertMulticooker(testMulticooker);
    }

    @Test
    void insertMulticooker_then_trows_DataAccessException() {
        Multicooker testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(multicookerRepoEM.insertMulticooker(testMulticooker)
        ).thenThrow(new DataAccessException("There is DataAccessException") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.insertMulticooker(testMulticooker));

        verify(multicookerRepoEM, new Times(1)
        ).insertMulticooker(testMulticooker);
    }

    @Test
    void readALL_then_return() {
        Multicooker testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        Multicooker updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());
        List<Multicooker> listMulticooker = new ArrayList<>(Arrays.asList(testMulticooker, updateMulticooker));

        when(multicookerRepoEM.readAll()
        ).thenReturn(listMulticooker);

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, listMulticooker);

        verify(multicookerRepoEM, new Times(1)).readAll();
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(multicookerRepoEM.readAll()
        ).thenThrow(new DataAccessException("There exception") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(multicookerRepoEM, new Times(1)).readAll();
    }

    @Test
    void deleteAll_then_return_emptyList() {

        service.deleteALL();

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(multicookerRepoEM, new Times(1)).deleteALL();
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {
        Mockito.doThrow(new DataAccessException("There is exception") {
        }).when(multicookerRepoEM).deleteALL();

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteALL());

        verify(multicookerRepoEM, new Times(1)).deleteALL();
    }
}