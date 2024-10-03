package by.ita.je.services;

import by.ita.je.models.Fridge;
import by.ita.je.repository.FridgeRepoCrud;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FridgeServiceTest extends TestUtils {
    @Mock
    private FridgeRepoCrud fridgeRepoCrud;
    @InjectMocks
    private FridgeService service;

    @Test
    void findFridgeByNumber_then_return_object() {
        Fridge testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());
        testFridge.setNumber(2);
        Integer number = 2;
        when(fridgeRepoCrud.findById(
                        eq(number)
                )
        ).thenReturn(Optional.of(testFridge));

        Fridge actual = service.findFridgeByNumber(number);

        assertEquals(actual, testFridge);

        verify(
                fridgeRepoCrud, new Times(1)).findById(
                eq(number)
        );
    }

    @Test
    void findFridgeByNumber_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(fridgeRepoCrud.findById(number)
        ).thenReturn(Optional.empty());

        Fridge actual = service.findFridgeByNumber(number);

        assertNull(actual);

        verify(
                fridgeRepoCrud, new Times(1)).findById(
                eq(number)
        );
    }

    @Test
    void findFridgeByNumber_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(fridgeRepoCrud.findById(
                        eq(number)
                )
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findFridgeByNumber(number));
        verify(
                fridgeRepoCrud, new Times(1)).findById(
                eq(number)
        );
    }

    @Test
    void updateFridge_then_return() {
        Fridge updateFridge = buildFridge("slim", "So so", true, true, BigDecimal.valueOf(1000.8), 'A', ZonedDateTime.parse("2019-11-19T19:19:54+02"));
        when(fridgeRepoCrud.findById(
                        eq(updateFridge.getNumber())
                )
        ).thenReturn(Optional.of(updateFridge));

        when(fridgeRepoCrud.save(
                eq(updateFridge)
        )).thenReturn(updateFridge);

        Fridge actual = service.updateFridge(updateFridge);
        Assertions.assertEquals(actual, updateFridge);

        verify(fridgeRepoCrud, new Times(1)).findById(updateFridge.getNumber());
        verify(fridgeRepoCrud, new Times(1)).save(updateFridge);
    }

    @Test
    void updateFridge_then_throws_DataAccessException() {
        Fridge updateFridge = buildFridge("slim", "So so", true, true, BigDecimal.valueOf(1000.8), 'A', ZonedDateTime.now());

        when(fridgeRepoCrud.findById(
                eq(updateFridge.getNumber())
        )).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.updateFridge(updateFridge));

        verify(fridgeRepoCrud, new Times(1)).findById(
                eq(updateFridge.getNumber())
        );
        verify(fridgeRepoCrud, new Times(0)).save(
                eq(updateFridge)
        );
    }

    @Test
    void deleteFridge_then_return() {
        Fridge testFridge = buildFridge("noFrost", "OK fridge", false, true, BigDecimal.valueOf(2500.5), 'A', ZonedDateTime.parse("2024-08-19T10:23:54+02"));

        when(fridgeRepoCrud.findById(
                eq(testFridge.getNumber())
        )).thenReturn(Optional.of(testFridge));
        doNothing().when(fridgeRepoCrud).deleteById(testFridge.getNumber());

        Fridge actual = service.deleteFridge(testFridge.getNumber());

        Assertions.assertEquals(actual, testFridge);

        verify(fridgeRepoCrud, new Times(1)).findById(
                eq(testFridge.getNumber())
        );
        verify(fridgeRepoCrud, new Times(1)).deleteById(
                eq(testFridge.getNumber())
        );
    }

    @Test
    void deleteFridge_then_return_null() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(fridgeRepoCrud.findById(
                        eq(number)
                )
        ).thenReturn(Optional.empty());

        Fridge actual = service.findFridgeByNumber(number);

        assertNull(actual);

        verify(fridgeRepoCrud, new Times(1)).findById(
                eq(number)
        );

        verify(fridgeRepoCrud, new Times(0)
        ).deleteById(
                eq(number)
        );
    }

    @Test
    void deleteFridge_then_trows_DataAccessException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        when(fridgeRepoCrud.findById(
                        eq(number)
                )
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.deleteFridge(number));

        verify(fridgeRepoCrud, new Times(1)).findById(
                eq(number)
        );

        verify(fridgeRepoCrud, new Times(0)
        ).deleteById(
                eq(number)
        );
    }

    @Test
    void insertFridge_then_return() {
        Fridge testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.parse("2023-05-13T07:50:54+02:00"));

        testFridge.setNumber(6);
        when(fridgeRepoCrud.save(testFridge)
        ).thenReturn(testFridge);

        Fridge actual = service.insertFridge();

        Assertions.assertEquals(actual, testFridge);

        verify(fridgeRepoCrud, new Times(1)).save(
                eq(testFridge)
        );
    }

    @Test
    void readALL_then_return() {
        Fridge testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());
        Fridge updateFridge = buildFridge("slim", "So so", true, true, BigDecimal.valueOf(1000.8), 'A', ZonedDateTime.now());
        List<Fridge> listFridge = new ArrayList<>(Arrays.asList(updateFridge, testFridge));

        when(fridgeRepoCrud.findAll()
        ).thenReturn(listFridge);

        List<Fridge> actualList = service.readALL();

        Assertions.assertEquals(actualList, listFridge);

        verify(fridgeRepoCrud, new Times(1)).findAll();
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(fridgeRepoCrud.findAll()
        ).thenThrow(new DataAccessException("") {
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(fridgeRepoCrud, new Times(1)).findAll();
    }

    @Test
    void deleteAll_then_return_emptyList() {
        doNothing().when(fridgeRepoCrud).deleteAll();

        service.deleteALL();

        List<Fridge> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(fridgeRepoCrud, new Times(1)).deleteAll();

    }
}