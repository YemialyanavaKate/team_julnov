package by.ita.je.services;

import by.ita.je.models.Kettle;
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
class KettleServiceTest extends TestUtils {
    
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private KettleService service;

    @Test
    void findKettleByNumber_then_return_notNull() {
        testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        Mockito.when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(testKettle);

        Kettle actual = service.findKettleByNumber(number);

        assertEquals(actual, testKettle);

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findKettleByNumber_then_return_null() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Kettle actual = service.findKettleByNumber(number);

        assertNull(actual);

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findKettleByNumber_then_trows_DataAccessException() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.findKettleByNumber(number));

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findKettleByNumber_then_trows_EmptyResultDataAccessException() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(1));

        Kettle actual = service.findKettleByNumber(number);

        assertNull(actual);

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void updateKettle_then_return() {
        updateKettle = buildKettle("glass", "pink", true, true, BigDecimal.valueOf(122.22), 'A', ZonedDateTime.parse("2011-11-11T11:11:11+02"));

        Mockito.when(jdbcTemplate.update(
                eq(SQL_UPDATE_KETTLE),
                eq(updateKettle.getType()),
                eq(updateKettle.getColor()),
                eq(updateKettle.getIsElectric()),
                eq(updateKettle.getIsInduction()),
                eq(updateKettle.getPrice()),
                eq(updateKettle.getEnergy()),
                eq(updateKettle.getRegistered()),
                eq(updateKettle.getNumber())
                )
        ).thenReturn(1);

        Mockito.when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(updateKettle.getNumber())
                )
        ).thenReturn(updateKettle);

        Kettle actual = service.updateKettle(updateKettle);

        Assertions.assertEquals(actual,updateKettle);
        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_KETTLE),
                eq(updateKettle.getType()),
                eq(updateKettle.getColor()),
                eq(updateKettle.getIsElectric()),
                eq(updateKettle.getIsInduction()),
                eq(updateKettle.getPrice()),
                eq(updateKettle.getEnergy()),
                eq(updateKettle.getRegistered()),
                eq(updateKettle.getNumber())
        );
        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateKettle.getNumber())
        );
    }

    @Test
    void updateKettle_then_throws_DataAccessException() {
        updateKettle = buildKettle("glass", "pink", true, true, BigDecimal.valueOf(122.22), 'A', ZonedDateTime.parse("2011-11-11T11:11:11+02"));

        Mockito.when(jdbcTemplate.update(
                        eq(SQL_UPDATE_KETTLE),
                        eq(updateKettle.getType()),
                        eq(updateKettle.getColor()),
                        eq(updateKettle.getIsElectric()),
                        eq(updateKettle.getIsInduction()),
                        eq(updateKettle.getPrice()),
                        eq(updateKettle.getEnergy()),
                        eq(updateKettle.getRegistered()),
                        eq(updateKettle.getNumber())
                )
        ).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class, () -> service.updateKettle(updateKettle));
        verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_UPDATE_KETTLE),
                eq(updateKettle.getType()),
                eq(updateKettle.getColor()),
                eq(updateKettle.getIsElectric()),
                eq(updateKettle.getIsInduction()),
                eq(updateKettle.getPrice()),
                eq(updateKettle.getEnergy()),
                eq(updateKettle.getRegistered()),
                eq(updateKettle.getNumber())
        );
        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(updateKettle.getNumber())
        );
    }

    @Test
    void deleteKettle_then_return() {
        testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
        )
        ).thenReturn(testKettle);

        when(jdbcTemplate.update(
                                eq(SQL_DELETE_KETTLE),
                                eq(testKettle.getNumber())
                )
                )
                .thenReturn(1);

        Kettle actual = service.deleteKettle(number);

        Assertions.assertEquals(actual,testKettle);

        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );

        Mockito.verify(jdbcTemplate, new Times(1)).update(
                eq(SQL_DELETE_KETTLE),
                eq(testKettle.getNumber())
        );

    }

    @Test
    void deleteKettle_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Kettle actual = service.deleteKettle(number);

        assertNull(actual);
        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );

        Mockito.verify(jdbcTemplate, new Times(0)).update(
                eq(SQL_DELETE_KETTLE),
                eq(number)
        );
    }

    @Test
    void deleteKettle_then_throws_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("Boo") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteKettle(number));

        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );

        Mockito.verify(jdbcTemplate, new Times(0)).update(
                eq(SQL_DELETE_KETTLE),
                eq(number)
        );
    }

    @Test
    void insertKettle_then_return() {
        testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        when(jdbcTemplate.update(
                eq(SQL_INSERT_KETTLE),
                eq(testKettle.getNumber()),
                eq(testKettle.getType()),
                eq(testKettle.getColor()),
                eq(testKettle.getIsElectric()),
                eq(testKettle.getIsInduction()),
                eq(testKettle.getPrice()),
                eq(testKettle.getEnergy()),
                eq(testKettle.getRegistered())
        )
        ).thenReturn(1);
        when(jdbcTemplate.queryForObject(
                        eq(SQL_SELECT_KETTLE),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(testKettle.getNumber())
        )
        ).thenReturn(testKettle);

        Kettle actual = service.insertKettle(testKettle);

        Assertions.assertEquals(actual,testKettle);

        verify(jdbcTemplate,new Times(1)).update(
                eq(SQL_INSERT_KETTLE),
                eq(testKettle.getNumber()),
                eq(testKettle.getType()),
                eq(testKettle.getColor()),
                eq(testKettle.getIsElectric()),
                eq(testKettle.getIsInduction()),
                eq(testKettle.getPrice()),
                eq(testKettle.getEnergy()),
                eq(testKettle.getRegistered())
        );

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(testKettle.getNumber())
        );
    }

    @Test
    void insertKettle_then_trows_DataAccessException() {
        testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        when(jdbcTemplate.update(
                eq(SQL_INSERT_KETTLE),
                eq(testKettle.getNumber()),
                eq(testKettle.getType()),
                eq(testKettle.getColor()),
                eq(testKettle.getIsElectric()),
                eq(testKettle.getIsInduction()),
                eq(testKettle.getPrice()),
                eq(testKettle.getEnergy()),
                eq(testKettle.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException"){});


        Assertions.assertThrows(DataAccessException.class, () -> service.insertKettle(testKettle));

        verify(jdbcTemplate,new Times(1)).update(
                eq(SQL_INSERT_KETTLE),
                eq(testKettle.getNumber()),
                eq(testKettle.getType()),
                eq(testKettle.getColor()),
                eq(testKettle.getIsElectric()),
                eq(testKettle.getIsInduction()),
                eq(testKettle.getPrice()),
                eq(testKettle.getEnergy()),
                eq(testKettle.getRegistered())
        );

        verify(jdbcTemplate, new Times(0)).queryForObject(
                eq(SQL_SELECT_KETTLE),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(0)
        );
    }

    @Test
    void readALL_then_return() {
        testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));
        updateKettle = buildKettle("glass", "pink", true, true, BigDecimal.valueOf(122.22), 'A', ZonedDateTime.parse("2011-11-11T11:11:11+02"));
        listKettle = new ArrayList<>(Arrays.asList(updateKettle,testKettle));

        when(jdbcTemplate.query(
                        eq("SELECT * FROM KETTLE"),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>)
        )
        ).thenReturn(listKettle);

        List<Kettle> actualList = service.readALL();

        Assertions.assertEquals(actualList,listKettle);
        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM KETTLE"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                eq("SELECT * FROM KETTLE"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>)
        )).thenThrow(new DataAccessException("There exception"){});

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM KETTLE"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>)
        );
    }

    @Test
    void deleteAll_then_emptyList() {

        Mockito.when(jdbcTemplate.update(
                        eq("DELETE FROM KETTLE")
                )
        ).thenReturn(1);

        service.deleteAll();

        List<Kettle> actualList = service.readALL();

        Assertions.assertEquals(actualList, Collections.emptyList());

        Mockito.verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM KETTLE")
        );
    }

    @Test
    void deleteAll_then_throws_DataAccessException() {

        Mockito.when(jdbcTemplate.update(
                        eq("DELETE FROM KETTLE")
                )
        ).thenThrow(new DataAccessException("There is exception") {});

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteAll());

        Mockito.verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM KETTLE")
        );
    }
}