package by.ita.je.services;

import by.ita.je.models.Fridge;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FridgeServiceTest extends TestUtils {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private FridgeService service;

    @Test
    void findFridgeByNumber_then_return_object() {
        testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());

                when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(testFridge);

        Fridge actual = service.findFridgeByNumber(number);

        assertEquals(actual,testFridge);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                        eq(SQL_SELECT_FRIDGE),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );
    }

    @Test
    void findFridgeByNumber_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_FRIDGE),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Fridge actual = service.findFridgeByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findFridgeByNumber_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_FRIDGE),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception"){});

        Assertions.assertThrows(DataAccessException.class, () -> service.findFridgeByNumber(number));
        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findFridgeByNumber_then_trows_EmptyResultDataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_FRIDGE),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(0));

        Fridge actual = service.findFridgeByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }
    @Test
    void updateFridge_then_return() {
        updateFridge = buildFridge("slim", "So so", true, true, BigDecimal.valueOf(1000.8), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_UPDATE_FRIDGE),
                eq(updateFridge.getType()),
                eq(updateFridge.getDescription()),
                eq(updateFridge.getDiscount()),
                eq(updateFridge.getDefect()),
                eq(updateFridge.getPrice()),
                eq(updateFridge.getEnergy()),
                eq(updateFridge.getRegistered()),
                eq(updateFridge.getNumber())
        )).thenReturn(2);

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateFridge.getNumber())
        )).thenReturn(updateFridge);

        Fridge actual = service.updateFridge(updateFridge);

        Assertions.assertEquals(actual,updateFridge);

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_FRIDGE),
                eq(updateFridge.getType()),
                eq(updateFridge.getDescription()),
                eq(updateFridge.getDiscount()),
                eq(updateFridge.getDefect()),
                eq(updateFridge.getPrice()),
                eq(updateFridge.getEnergy()),
                eq(updateFridge.getRegistered()),
                eq(updateFridge.getNumber())
        );
        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateFridge.getNumber())
        );
    }

    @Test
    void updateFridge_then_throws_DataAccessException() {
        updateFridge = buildFridge("slim", "So so", true, true, BigDecimal.valueOf(1000.8), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_UPDATE_FRIDGE),
                eq(updateFridge.getType()),
                eq(updateFridge.getDescription()),
                eq(updateFridge.getDiscount()),
                eq(updateFridge.getDefect()),
                eq(updateFridge.getPrice()),
                eq(updateFridge.getEnergy()),
                eq(updateFridge.getRegistered()),
                eq(updateFridge.getNumber())
        )).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.updateFridge(updateFridge));

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_FRIDGE),
                eq(updateFridge.getType()),
                eq(updateFridge.getDescription()),
                eq(updateFridge.getDiscount()),
                eq(updateFridge.getDefect()),
                eq(updateFridge.getPrice()),
                eq(updateFridge.getEnergy()),
                eq(updateFridge.getRegistered()),
                eq(updateFridge.getNumber())
        );
        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateFridge.getNumber())
        );
    }

    @Test
    void deleteFridge_then_return() {
        testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_FRIDGE),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
                ).thenReturn(testFridge);

        when(jdbcTemplate.update(
                        eq(SQL_DELETE_FRIDGE),
                        eq(testFridge.getNumber())
                )
                ).thenReturn(1);

        Fridge actual = service.deleteFridge(number);

        Assertions.assertEquals(actual, testFridge);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_DELETE_FRIDGE),
                eq(testFridge.getNumber())
        );
    }

   @Test
   void deleteFridge_then_return_null() {

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(null);

       Fridge actual = service.findFridgeByNumber(number);

       assertNull(actual);

       verify(jdbcTemplate, new Times(1)).queryForObject(
               eq(SQL_SELECT_FRIDGE),
               (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
               eq(number)
       );

       verify(jdbcTemplate, new Times(0)
        ).update(
               eq(SQL_DELETE_FRIDGE),
               eq(number)
        );
    }

    @Test
    void deleteFridge_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_FRIDGE),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There is exception"){});

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteFridge(number));

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );

        verify(jdbcTemplate, new Times(0)
        ).update(
                eq(SQL_DELETE_FRIDGE),
                eq(number)
        );
    }

    @Test
    void insertFridge_then_return() {
        testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_INSERT_FRIDGE),
                eq(testFridge.getNumber()),
                eq(testFridge.getType()),
                eq(testFridge.getDescription()),
                eq(testFridge.getDiscount()),
                eq(testFridge.getDefect()),
                eq(testFridge.getPrice()),
                eq(testFridge.getEnergy()),
                eq(testFridge.getRegistered())
        )
        ).thenReturn(1);
        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(testFridge.getNumber())
        )
        ).thenReturn(testFridge);

        Fridge actual = service.insertFridge(testFridge);

        Assertions.assertEquals(actual, testFridge);

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_INSERT_FRIDGE),
                eq(testFridge.getNumber()),
                eq(testFridge.getType()),
                eq(testFridge.getDescription()),
                eq(testFridge.getDiscount()),
                eq(testFridge.getDefect()),
                eq(testFridge.getPrice()),
                eq(testFridge.getEnergy()),
                eq(testFridge.getRegistered())
        );

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(testFridge.getNumber())
        );
    }

    @Test
    void insertFridge_then_trows_DataAccessException() {
        testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                        eq(SQL_INSERT_FRIDGE),
                        eq(testFridge.getNumber()),
                        eq(testFridge.getType()),
                        eq(testFridge.getDescription()),
                        eq(testFridge.getDiscount()),
                        eq(testFridge.getDefect()),
                        eq(testFridge.getPrice()),
                        eq(testFridge.getEnergy()),
                        eq(testFridge.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.insertFridge(testFridge));

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(SQL_INSERT_FRIDGE),
                eq(testFridge.getNumber()),
                eq(testFridge.getType()),
                eq(testFridge.getDescription()),
                eq(testFridge.getDiscount()),
                eq(testFridge.getDefect()),
                eq(testFridge.getPrice()),
                eq(testFridge.getEnergy()),
                eq(testFridge.getRegistered())
        );

        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_FRIDGE),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void readALL_then_return() {
        testFridge = buildFridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 'A', ZonedDateTime.now());
        updateFridge = buildFridge("slim", "So so", true, true, BigDecimal.valueOf(1000.8), 'A', ZonedDateTime.now());
        listFridge = new ArrayList<>(Arrays.asList(updateFridge,testFridge));

        when(jdbcTemplate.query(
                eq("SELECT * FROM FRIDGE"),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>)
        )
        ).thenReturn(listFridge);

        List<Fridge> actualList = service.readALL();

        Assertions.assertEquals(actualList, listFridge);

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM FRIDGE"),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                        eq("SELECT * FROM FRIDGE"),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>)
                )
        ).thenThrow(new DataAccessException("There exception") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM FRIDGE"),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void deleteAll_then_return_emptyList() {

        when(jdbcTemplate.update(
                        eq("DELETE FROM FRIDGE")
                )
        ).thenReturn(1);

        service.deleteALL();

        List<Fridge> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM FRIDGE")
        );
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        when(jdbcTemplate.update(
                        eq("DELETE FROM FRIDGE")
                )
        ).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteALL());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM FRIDGE")
        );
    }
}