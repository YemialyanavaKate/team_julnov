package by.ita.je.services;

import by.ita.je.models.TV;
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
class TVServiceTest extends TestUtils {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TVService service;

    @Test
    void findTVByNumber_then_return_notNull() {
        testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(testTV);

        TV actual = service.findTVByNumber(number);

        assertEquals(actual, testTV);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                        eq(SQL_SELECT_TV),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );
    }

    @Test
    void findTVByNumber_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_TV),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        TV actual = service.findTVByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findTVByNumber_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_TV),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception"){});

        Assertions.assertThrows(DataAccessException.class, () -> service.findTVByNumber(number));
        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findTVByNumber_then_trows_EmptyResultDataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_TV),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(0));

        TV actual = service.findTVByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }
    @Test
    void updateTV_then_return() {
        updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_UPDATE_TV),
                eq(updateTV.getType()),
                eq(updateTV.getBrand()),
                eq(updateTV.getDiscount()),
                eq(updateTV.getDiagonal()),
                eq(updateTV.getPrice()),
                eq(updateTV.getEnergy()),
                eq(updateTV.getRegistered()),
                eq(updateTV.getNumber())
        )).thenReturn(2);

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateTV.getNumber())
        )).thenReturn(updateTV);

        TV actual = service.updateTV(updateTV);

        Assertions.assertEquals(actual,updateTV);

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_TV),
                eq(updateTV.getType()),
                eq(updateTV.getBrand()),
                eq(updateTV.getDiscount()),
                eq(updateTV.getDiagonal()),
                eq(updateTV.getPrice()),
                eq(updateTV.getEnergy()),
                eq(updateTV.getRegistered()),
                eq(updateTV.getNumber())
        );
        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateTV.getNumber())
        );
    }

    @Test
    void updateTV_then_throws_DataAccessException() {
        updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_UPDATE_TV),
                eq(updateTV.getType()),
                eq(updateTV.getBrand()),
                eq(updateTV.getDiscount()),
                eq(updateTV.getDiagonal()),
                eq(updateTV.getPrice()),
                eq(updateTV.getEnergy()),
                eq(updateTV.getRegistered()),
                eq(updateTV.getNumber())
        )).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.updateTV(updateTV));

        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_TV),
                eq(updateTV.getType()),
                eq(updateTV.getBrand()),
                eq(updateTV.getDiscount()),
                eq(updateTV.getDiagonal()),
                eq(updateTV.getPrice()),
                eq(updateTV.getEnergy()),
                eq(updateTV.getRegistered()),
                eq(updateTV.getNumber())
        );
        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateTV.getNumber())
        );
    }

    @Test
    void deleteTV_then_return() {
        testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_TV),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
                ).thenReturn(testTV);

        when(jdbcTemplate.update(
                        eq(SQL_DELETE_TV),
                        eq(number)
                )
                ).thenReturn(1);

        TV actual = service.deleteTV(number);

        Assertions.assertEquals(actual, testTV);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_DELETE_TV),
                eq(number)
        );
    }

   @Test
   void deleteTV_then_return_null() {

        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(null);

       TV actual = service.findTVByNumber(number);

       assertNull(actual);

       verify(jdbcTemplate, new Times(1)).queryForObject(
               eq(SQL_SELECT_TV),
               (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
               eq(number)
       );

       verify(jdbcTemplate, new Times(0)
        ).update(
                any(String.class),
                any(Integer.class)
        );
    }

    @Test
    void deleteTV_then_trows_DataAccessException() {
        testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_TV),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(testTV);

        when(jdbcTemplate.update(
                eq(SQL_DELETE_TV),
                eq(number)
        )
        ).thenThrow(new DataAccessException("There is exception"){}
        );

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteTV(number));

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(SQL_DELETE_TV),
                eq(number)
        );
    }

    @Test
    void insertTV_then_return() {
        testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                eq(SQL_INSERT_TV),
                eq(testTV.getNumber()),
                eq(testTV.getType()),
                eq(testTV.getBrand()),
                eq(testTV.getDiscount()),
                eq(testTV.getDiagonal()),
                eq(testTV.getPrice()),
                eq(testTV.getEnergy()),
                eq(testTV.getRegistered())
        )
        ).thenReturn(1);
        when(jdbcTemplate.queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        )
        ).thenReturn(testTV);

        TV actual = service.insertTV(testTV);

        Assertions.assertEquals(actual, testTV);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(SQL_INSERT_TV),
                eq(testTV.getNumber()),
                eq(testTV.getType()),
                eq(testTV.getBrand()),
                eq(testTV.getDiscount()),
                eq(testTV.getDiagonal()),
                eq(testTV.getPrice()),
                eq(testTV.getEnergy()),
                eq(testTV.getRegistered())
        );
    }

    @Test
    void insertTV_then_trows_DataAccessException() {
        testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());

        when(jdbcTemplate.update(
                        eq(SQL_INSERT_TV),
                        eq(testTV.getNumber()),
                        eq(testTV.getType()),
                        eq(testTV.getBrand()),
                        eq(testTV.getDiscount()),
                        eq(testTV.getDiagonal()),
                        eq(testTV.getPrice()),
                        eq(testTV.getEnergy()),
                        eq(testTV.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.insertTV(testTV));

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(SQL_INSERT_TV),
                eq(testTV.getNumber()),
                eq(testTV.getType()),
                eq(testTV.getBrand()),
                eq(testTV.getDiscount()),
                eq(testTV.getDiagonal()),
                eq(testTV.getPrice()),
                eq(testTV.getEnergy()),
                eq(testTV.getRegistered())
        );

        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_TV),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void readALL_then_return() {
        testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.now());
        updateTV = buildTV("TV", "Horisont", true, 32, BigDecimal.valueOf(1222.8), 'B', ZonedDateTime.now());
        listTV = new ArrayList<>(Arrays.asList(testTV, updateTV));

        when(jdbcTemplate.query(
                eq("SELECT * FROM TV"),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>)
        )
        ).thenReturn(listTV);

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, listTV);

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM TV"),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                        eq("SELECT * FROM TV"),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>)
                )
        ).thenThrow(new DataAccessException("There exception") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM TV"),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void deleteAll_then_return_emptyList() {

        when(jdbcTemplate.update(
                        eq("DELETE FROM TV")
                )
        ).thenReturn(1);

        service.deleteAll();

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM TV")
        );
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        when(jdbcTemplate.update(
                        eq("DELETE FROM TV")
                )
        ).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteAll());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM TV")
        );
    }
}