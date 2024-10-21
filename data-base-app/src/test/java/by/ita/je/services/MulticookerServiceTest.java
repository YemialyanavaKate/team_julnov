package by.ita.je.services;

import by.ita.je.models.Multicooker;
import by.ita.je.repository.MulticookerCrudRepository;
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
class MulticookerServiceTest extends TestUtils {

    @Mock
    private MulticookerCrudRepository multicookerCrud;
    @InjectMocks
    private MulticookerService service;

    @Test
    void findMulticookerByNumber_then_return() {
        Multicooker testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        testMulticooker.setNumber(2);
        Integer number = 2;
        when(multicookerCrud.findById(number)
        ).thenReturn(Optional.of(testMulticooker));

        Multicooker actual = service.findMulticookerByNumber(number);

        assertEquals(actual, testMulticooker);

        verify(
                multicookerCrud, new Times(1)).findById(number);
    }

    @Test
    void findMulticookerByNumber_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(multicookerCrud.findById(number)
        ).thenReturn(Optional.empty());

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNull(actual);

        verify(
                multicookerCrud, new Times(1)).findById(number);
    }

    @Test
    void findMulticookerByNumber_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(multicookerCrud.findById(number))
                .thenThrow(new DataAccessException("There exception") {
                });

        Assertions.assertThrows(DataAccessException.class, () -> service.findMulticookerByNumber(number));
        verify(multicookerCrud, new Times(1)).findById(number);
    }

    @Test
    void updateMulticooker_then_return() {
        Multicooker updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());

        when(multicookerCrud.findById(updateMulticooker.getNumber())
        ).thenReturn(Optional.of(updateMulticooker));
        when(multicookerCrud.save(updateMulticooker)
        ).thenReturn(updateMulticooker);

        Multicooker actual = service.updateMulticooker(updateMulticooker);

        Assertions.assertEquals(actual, updateMulticooker);
        verify(multicookerCrud, new Times(1)).findById(updateMulticooker.getNumber());
        verify(multicookerCrud, new Times(1)).save(updateMulticooker);
    }

    @Test
    void updateMulticooker_then_throws_DataAccessException() {
        Multicooker updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());

        when(multicookerCrud.findById(updateMulticooker.getNumber())
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.updateMulticooker(updateMulticooker));

        verify(multicookerCrud, new Times(1)).findById(updateMulticooker.getNumber());
        verify(multicookerCrud, new Times(0)).save(updateMulticooker);
    }

    @Test
    void deleteMulticooker_then_return() {
        Multicooker testMulticooker = buildMulticooker("NonRemovablePanels", "for sweet", false, 20001, BigDecimal.valueOf(99.8), 'A', ZonedDateTime.parse("2024-05-11T23:23:54+02"));

        when(multicookerCrud.findById(testMulticooker.getNumber())).thenReturn(Optional.of(testMulticooker));
        doNothing().when(multicookerCrud).deleteById(testMulticooker.getNumber());

        Multicooker actual = service.deleteMulticooker(testMulticooker.getNumber());

        Assertions.assertEquals(actual, testMulticooker);

        verify(multicookerCrud, new Times(1)).findById(testMulticooker.getNumber());
        verify(multicookerCrud, new Times(1)).deleteById(testMulticooker.getNumber());

    }

    @Test
    void deleteMulticooker_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(multicookerCrud.findById(number)
        ).thenReturn(Optional.empty());

        Multicooker actual = service.deleteMulticooker(number);

        assertNull(actual);

        verify(multicookerCrud, new Times(1)).findById(number);
        verify(multicookerCrud, new Times(0)).deleteById(number);
    }

    @Test
    void deleteMulticooker_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(multicookerCrud.findById(number))
                .thenThrow(new DataAccessException("") {
                });

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteMulticooker(number));

        verify(multicookerCrud, new Times(1)).findById(number);
        verify(multicookerCrud, new Times(0)).deleteById(number);
    }

    @Test
    void insertMulticooker_then_return() {
        Multicooker testMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1112), 'B', ZonedDateTime.parse("2024-01-01T10:23:54+02"));
        testMulticooker.setNumber(5);
        when(multicookerCrud.save(testMulticooker))
                .thenReturn(testMulticooker);

        Multicooker actual = service.insertMulticooker();

        Assertions.assertEquals(actual, testMulticooker);

        verify(multicookerCrud, new Times(1)).save(testMulticooker);
    }


    @Test
    void readALL_then_return() {
        Multicooker testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        Multicooker updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());
        List<Multicooker> listMulticooker = new ArrayList<>(Arrays.asList(testMulticooker, updateMulticooker));

        when(multicookerCrud.findAll()
        ).thenReturn(listMulticooker);

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, listMulticooker);

        verify(multicookerCrud, new Times(1)).findAll();
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(multicookerCrud.findAll()
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(multicookerCrud, new Times(1)).findAll();
    }

    @Test
    void deleteAll_then_return_emptyList() {

        service.deleteALL();

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(multicookerCrud, new Times(1)).deleteAll();
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {
        Mockito.doThrow(new DataAccessException("There is exception") {
        }).when(multicookerCrud).deleteAll();

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteALL());

        verify(multicookerCrud, new Times(1)).deleteAll();
    }
}