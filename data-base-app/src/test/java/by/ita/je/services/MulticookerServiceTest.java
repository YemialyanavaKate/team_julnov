package by.ita.je.services;

import by.ita.je.models.Multicooker;
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
class MulticookerServiceTest extends TestUtils {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private MulticookerService service;

    @Test
    void findMulticookerByNumber_then_return() {
        testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(testMulticooker);

        Multicooker actual = service.findMulticookerByNumber(number);

        assertEquals(actual, testMulticooker);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                        eq(SQL_SELECT_MULTICOOKER),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );
    }

    @Test
    void findMulticookerByNumber_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_MULTICOOKER),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findMulticookerByNumber_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_MULTICOOKER),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception"){});

        Assertions.assertThrows(DataAccessException.class, () -> service.findMulticookerByNumber(number));
        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findMulticookerByNumber_then_trows_EmptyResultDataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_MULTICOOKER),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(0));

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }
    @Test
    void updateMulticooker_then_return() {
        updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_UPDATE_MULTICOOKER),
                eq(updateMulticooker.getType()),
                eq(updateMulticooker.getDescription()),
                eq(updateMulticooker.getIsTouchScreen()),
                eq(updateMulticooker.getNumberModes()),
                eq(updateMulticooker.getPrice()),
                eq(updateMulticooker.getEnergy()),
                eq(updateMulticooker.getRegistered()),
                eq(updateMulticooker.getNumber())
        )).thenReturn(2);

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateMulticooker.getNumber())
        )).thenReturn(updateMulticooker);

        Multicooker actual = service.updateMulticooker(updateMulticooker);

        Assertions.assertEquals(actual,updateMulticooker);

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_MULTICOOKER),
                eq(updateMulticooker.getType()),
                eq(updateMulticooker.getDescription()),
                eq(updateMulticooker.getIsTouchScreen()),
                eq(updateMulticooker.getNumberModes()),
                eq(updateMulticooker.getPrice()),
                eq(updateMulticooker.getEnergy()),
                eq(updateMulticooker.getRegistered()),
                eq(updateMulticooker.getNumber())
        );
        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateMulticooker.getNumber())
        );
    }

    @Test
    void updateMulticooker_then_throws_DataAccessException() {
        updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_UPDATE_MULTICOOKER),
                eq(updateMulticooker.getType()),
                eq(updateMulticooker.getDescription()),
                eq(updateMulticooker.getIsTouchScreen()),
                eq(updateMulticooker.getNumberModes()),
                eq(updateMulticooker.getPrice()),
                eq(updateMulticooker.getEnergy()),
                eq(updateMulticooker.getRegistered()),
                eq(updateMulticooker.getNumber())
        )).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.updateMulticooker(updateMulticooker));

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_MULTICOOKER),
                eq(updateMulticooker.getType()),
                eq(updateMulticooker.getDescription()),
                eq(updateMulticooker.getIsTouchScreen()),
                eq(updateMulticooker.getNumberModes()),
                eq(updateMulticooker.getPrice()),
                eq(updateMulticooker.getEnergy()),
                eq(updateMulticooker.getRegistered()),
                eq(updateMulticooker.getNumber())
        );
        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateMulticooker.getNumber())
        );
    }

    @Test
    void deleteMulticooker_then_return() {
        testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());


        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_MULTICOOKER),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
                ).thenReturn(testMulticooker);

        when(jdbcTemplate.update(
                        eq(SQL_DELETE_MULTICOOKER),
                        eq(testMulticooker.getNumber())
                )
                ).thenReturn(1);

        Multicooker actual = service.deleteMulticooker(number);

        Assertions.assertEquals(actual, testMulticooker);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_DELETE_MULTICOOKER),
                eq(testMulticooker.getNumber())
        );
    }

   @Test
   void deleteMulticooker_then_return_null() {

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(null);

       Multicooker actual = service.findMulticookerByNumber(number);

       assertNull(actual);

       verify(jdbcTemplate, new Times(1)).queryForObject(
               eq(SQL_SELECT_MULTICOOKER),
               (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
               eq(number)
       );

       verify(jdbcTemplate, new Times(0)
        ).update(
                any(String.class),
                any(Integer.class)
        );
    }

    @Test
    void deleteMulticooker_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_MULTICOOKER),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There is exception"){}
        );

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteMulticooker(number));

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );

        verify(jdbcTemplate, new Times(0)
        ).update(
                any(String.class),
                any(Integer.class)
        );
    }

    @Test
    void insertMulticooker_then_return() {
        testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_INSERT_MULTICOOKER),
                eq(testMulticooker.getNumber()),
                eq(testMulticooker.getType()),
                eq(testMulticooker.getDescription()),
                eq(testMulticooker.getIsTouchScreen()),
                eq(testMulticooker.getNumberModes()),
                eq(testMulticooker.getPrice()),
                eq(testMulticooker.getEnergy()),
                eq(testMulticooker.getRegistered())
        )
        ).thenReturn(1);
        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(testMulticooker.getNumber())
        )
        ).thenReturn(testMulticooker);

        Multicooker actual = service.insertMulticooker(testMulticooker);

        Assertions.assertEquals(actual, testMulticooker);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(testMulticooker.getNumber())
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(SQL_INSERT_MULTICOOKER),
                eq(testMulticooker.getNumber()),
                eq(testMulticooker.getType()),
                eq(testMulticooker.getDescription()),
                eq(testMulticooker.getIsTouchScreen()),
                eq(testMulticooker.getNumberModes()),
                eq(testMulticooker.getPrice()),
                eq(testMulticooker.getEnergy()),
                eq(testMulticooker.getRegistered())
        );
    }

    @Test
    void insertMulticooker_then_trows_DataAccessException() {
        testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                        eq(SQL_INSERT_MULTICOOKER),
                        eq(testMulticooker.getNumber()),
                        eq(testMulticooker.getType()),
                        eq(testMulticooker.getDescription()),
                        eq(testMulticooker.getIsTouchScreen()),
                        eq(testMulticooker.getNumberModes()),
                        eq(testMulticooker.getPrice()),
                        eq(testMulticooker.getEnergy()),
                        eq(testMulticooker.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.insertMulticooker(testMulticooker));

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(SQL_INSERT_MULTICOOKER),
                eq(testMulticooker.getNumber()),
                eq(testMulticooker.getType()),
                eq(testMulticooker.getDescription()),
                eq(testMulticooker.getIsTouchScreen()),
                eq(testMulticooker.getNumberModes()),
                eq(testMulticooker.getPrice()),
                eq(testMulticooker.getEnergy()),
                eq(testMulticooker.getRegistered())
        );

        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_MULTICOOKER),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void readALL_then_return() {
        testMulticooker = buildMulticooker("Tefal", "бла-бла-бла", true, 10, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        updateMulticooker = buildMulticooker("Midea", "So so", true, 91, BigDecimal.valueOf(1111.8), 'B', ZonedDateTime.now());
        listMulticooker = new ArrayList<>(Arrays.asList(testMulticooker, updateMulticooker));

        when(jdbcTemplate.query(
                eq("SELECT * FROM MULTICOOKER"),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>)
        )
        ).thenReturn(listMulticooker);

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, listMulticooker);

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM MULTICOOKER"),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                        eq("SELECT * FROM MULTICOOKER"),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>)
                )
        ).thenThrow(new DataAccessException("There exception") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM MULTICOOKER"),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void deleteAll_then_return_emptyList() {

        when(jdbcTemplate.update(
                        eq("DELETE FROM MULTICOOKER")
                )
        ).thenReturn(1);

        service.deleteALL();

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM MULTICOOKER")
        );
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        when(jdbcTemplate.update(
                        eq("DELETE FROM MULTICOOKER")
                )
        ).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteALL());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM MULTICOOKER")
        );
    }
}