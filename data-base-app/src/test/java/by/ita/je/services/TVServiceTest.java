package by.ita.je.services;

import by.ita.je.models.TV;
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
class TVServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TVService service;

    Random random = new Random();
    Integer number = random.nextInt(Integer.MAX_VALUE);
    
    TV testTV = TV.builder()
            .number(number)
            .type("TV")
            .brand("LG")
            .discount(false)
            .diagonal(15)
            .price(BigDecimal.valueOf(1000.5))
            .energy('A')
            .registered(ZonedDateTime.now())
            .build();

    TV updateTV = TV.builder()
            .number(3)
            .type("TV")
            .brand("Horisont")
            .discount(true)
            .diagonal(32)
            .price(BigDecimal.valueOf(1222.8))
            .energy('B')
            .registered(ZonedDateTime.now())
            .build();
    
    List<TV> list = new ArrayList<>();
    String sqlSelect = "SELECT * FROM TV WHERE number = ?";
    String sqlUpdate = "UPDATE TV SET type = ?, brand = ?, discount = ?, diagonal = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    String sqlDelete = "DELETE FROM TV WHERE number = ?";
    String sqlInsert = "INSERT INTO TV (number, type, brand, discount, diagonal, price, energy, registered)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    @Test
    void findTVByNumber_then_return_notNull() {

        when(jdbcTemplate.queryForObject(
                eq(sqlSelect),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(testTV);

        TV actual = service.findTVByNumber(number);

        assertNotNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                        eq(sqlSelect),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );
    }

    @Test
    void findTVByNumber_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        TV actual = service.findTVByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findTVByNumber_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception"){
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findTVByNumber(number));
        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findTVByNumber_then_trows_EmptyResultDataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(0));

        TV actual = service.findTVByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<TV>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }
    @Test
    void updateTV_then_return() {
        when(jdbcTemplate.update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateTV.getNumber())
        )).thenReturn(updateTV);

        TV actual = service.updateTV(updateTV);

        Assertions.assertEquals(actual,updateTV);

        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateTV.getNumber())
        );
    }

    @Test
    void updateTV_then_throws_DataAccessException() {
        when(jdbcTemplate.update(
                eq(sqlUpdate),
                eq(updateTV.getType()),
                eq(updateTV.getBrand()),
                eq(updateTV.getDiscount()),
                eq(updateTV.getDiagonal()),
                eq(updateTV.getPrice()),
                eq(updateTV.getEnergy()),
                eq(updateTV.getRegistered()),
                eq(updateTV.getNumber())
        )).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.updateTV(updateTV));

        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateTV.getNumber())
        );
    }

    @Test
    void deleteTV_then_return() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        any(RowMapper.class),
                        eq(number)
                )
                ).thenReturn(testTV);

        when(jdbcTemplate.update(
                        eq(sqlDelete),
                        eq(number)
                )
                ).thenReturn(1);

        TV actual = service.deleteTV(number);

        Assertions.assertEquals(actual, testTV);

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
   void deleteTV_then_return_null() {

        when(jdbcTemplate.queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
                )
        ).thenReturn(null);

       TV actual = service.findTVByNumber(number);

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
    void deleteTV_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        any(RowMapper.class),
                        eq(number)
                )
        ).thenReturn(testTV);

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

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteTV(number));

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
    void insertTV_then_return() {
        when(jdbcTemplate.update(
                eq(sqlInsert),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        )
        ).thenReturn(testTV);

        TV actual = service.insertTV(testTV);

        Assertions.assertEquals(actual, testTV);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(sqlInsert),
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
        when(jdbcTemplate.update(
                        eq(sqlInsert),
                        eq(testTV.getNumber()),
                        eq(testTV.getType()),
                        eq(testTV.getBrand()),
                        eq(testTV.getDiscount()),
                        eq(testTV.getDiagonal()),
                        eq(testTV.getPrice()),
                        eq(testTV.getEnergy()),
                        eq(testTV.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.insertTV(testTV));

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(sqlInsert),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );
    }

    @Test
    void readALL_then_return() {
        list.add(updateTV);
        list.add(testTV);

        when(jdbcTemplate.query(
                eq("SELECT * FROM TV"),
                any(RowMapper.class)
        )
        ).thenReturn(list);

        List<TV> actualList = service.readALL();

        Assertions.assertEquals(actualList, list);

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM TV"),
                any(RowMapper.class)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                        eq("SELECT * FROM TV"),
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
                eq("SELECT * FROM TV"),
                any(RowMapper.class)
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
        ).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteAll());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM TV")
        );
    }
}