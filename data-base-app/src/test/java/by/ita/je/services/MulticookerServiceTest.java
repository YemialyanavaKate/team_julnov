package by.ita.je.services;

import by.ita.je.models.Multicooker;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MulticookerServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private MulticookerService service;

    Random random = new Random();
    Integer number = random.nextInt(Integer.MAX_VALUE);
    
    Multicooker testMulticooker = Multicooker.builder()
            .number(number)
            .type("Tefal")
            .description("бла-бла-бла")
            .isTouchScreen(true)
            .numberModes(10)
            .price(BigDecimal.valueOf(1000.5))
            .energy('A')
            .registered(ZonedDateTime.now())
            .build();

    Multicooker updateMulticooker = Multicooker.builder()
            .number(2)
            .type("Midea")
            .description("So so")
            .isTouchScreen(true)
            .numberModes(91)
            .price(BigDecimal.valueOf(1111.8))
            .energy('B')
            .registered(ZonedDateTime.now())
            .build();
    
    List<Multicooker> list = new ArrayList<>();
    String sqlSelect = "SELECT * FROM MULTICOOKER WHERE number = ?";
    String sqlUpdate = "UPDATE MULTICOOKER SET type = ?, description = ?, isTouchScreen = ?, numberModes = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    String sqlDelete = "DELETE FROM MULTICOOKER WHERE number = ?";
    String sqlInsert = "INSERT INTO MULTICOOKER (number, type, description, isTouchScreen, numberModes, price, energy, registered)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    @Test
    void findMulticookerByNumber_then_return_notNull() {

        when(jdbcTemplate.queryForObject(
                eq(sqlSelect),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(testMulticooker);

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNotNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );
    }

    @Test
    void findMulticookerByNumber_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findMulticookerByNumber_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception"){
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findMulticookerByNumber(number));
        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findMulticookerByNumber_then_trows_EmptyResultDataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(0));

        Multicooker actual = service.findMulticookerByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<Multicooker>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }
    @Test
    void updateMulticooker_then_return() {
        when(jdbcTemplate.update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateMulticooker.getNumber())
        )).thenReturn(updateMulticooker);

        Multicooker actual = service.updateMulticooker(updateMulticooker);

        Assertions.assertEquals(actual,updateMulticooker);

        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateMulticooker.getNumber())
        );
    }

    @Test
    void updateMulticooker_then_throws_DataAccessException() {
        when(jdbcTemplate.update(
                eq(sqlUpdate),
                eq(updateMulticooker.getType()),
                eq(updateMulticooker.getDescription()),
                eq(updateMulticooker.getIsTouchScreen()),
                eq(updateMulticooker.getNumberModes()),
                eq(updateMulticooker.getPrice()),
                eq(updateMulticooker.getEnergy()),
                eq(updateMulticooker.getRegistered()),
                eq(updateMulticooker.getNumber())
        )).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.updateMulticooker(updateMulticooker));

        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateMulticooker.getNumber())
        );
    }

    @Test
    void deleteMulticooker_then_return() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        any(RowMapper.class),
                        eq(number)
                )
                ).thenReturn(testMulticooker);

        when(jdbcTemplate.update(
                        eq(sqlDelete),
                        eq(number)
                )
                ).thenReturn(1);

        Multicooker actual = service.deleteMulticooker(number);

        Assertions.assertEquals(actual, testMulticooker);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );
        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlDelete),
                eq(number)
        );
    }

   @Test
   void deleteMulticooker_then_return_null() {

        when(jdbcTemplate.queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
                )
        ).thenReturn(null);

       Multicooker actual = service.findMulticookerByNumber(number);

       assertNull(actual);

       verify(jdbcTemplate, new Times(1)).queryForObject(
               eq(sqlSelect),
               any(RowMapper.class),
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
                        eq(sqlSelect),
                        any(RowMapper.class),
                        eq(number)
                )
        ).thenReturn(testMulticooker);

        when(jdbcTemplate.update(
                eq(sqlDelete),
                eq(number)
        )
        ).thenThrow(new DataAccessException("There is exception"){
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }
        );

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteMulticooker(number));

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                any(String.class),
                any(Integer.class)
        );
    }

    @Test
    void insertMulticooker_then_return() {
        when(jdbcTemplate.update(
                eq(sqlInsert),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        )
        ).thenReturn(testMulticooker);

        Multicooker actual = service.insertMulticooker(testMulticooker);

        Assertions.assertEquals(actual, testMulticooker);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(sqlInsert),
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
        when(jdbcTemplate.update(
                        eq(sqlInsert),
                        eq(testMulticooker.getNumber()),
                        eq(testMulticooker.getType()),
                        eq(testMulticooker.getDescription()),
                        eq(testMulticooker.getIsTouchScreen()),
                        eq(testMulticooker.getNumberModes()),
                        eq(testMulticooker.getPrice()),
                        eq(testMulticooker.getEnergy()),
                        eq(testMulticooker.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.insertMulticooker(testMulticooker));

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(sqlInsert),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );
    }

    @Test
    void readALL_then_return() {
        list.add(updateMulticooker);
        list.add(testMulticooker);

        when(jdbcTemplate.query(
                eq("SELECT * FROM MULTICOOKER"),
                any(RowMapper.class)
        )
        ).thenReturn(list);

        List<Multicooker> actualList = service.readALL();

        Assertions.assertEquals(actualList, list);

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM MULTICOOKER"),
                any(RowMapper.class)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                        eq("SELECT * FROM MULTICOOKER"),
                        any(RowMapper.class)
                )
        ).thenThrow(new DataAccessException("There exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM MULTICOOKER"),
                any(RowMapper.class)
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
        ).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteALL());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM MULTICOOKER")
        );
    }
}