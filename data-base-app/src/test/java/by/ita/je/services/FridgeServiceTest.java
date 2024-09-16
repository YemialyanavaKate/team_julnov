package by.ita.je.services;

import by.ita.je.models.Fridge;
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
class FridgeServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private FridgeService service;

    Random random = new Random();
    Integer number = random.nextInt(Integer.MAX_VALUE);
    
    Fridge testFridge = Fridge.builder()
            .number(number)
            .type("Samsung")
            .description("The best")
            .discount(false)
            .defect(false)
            .price(BigDecimal.valueOf(3000.99))
            .energy('A')
            .registered(ZonedDateTime.now())
            .build();

    Fridge updateFridge = Fridge.builder()
            .number(2)
            .type("slim")
            .description("So so")
            .discount(true)
            .defect(true)
            .price(BigDecimal.valueOf(1000.8))
            .energy('A')
            .registered(ZonedDateTime.now())
            .build();
    
    List<Fridge> list = new ArrayList<>();
    String sqlSelect = "SELECT * FROM FRIDGE WHERE number = ?";
    String sqlUpdate = "UPDATE FRIDGE SET type = ?, description = ?, discount = ?, defect = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    String sqlDelete = "DELETE FROM FRIDGE WHERE number = ?";
    String sqlInsert = "INSERT INTO FRIDGE(number, type, description, discount, defect, price, energy, registered) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    @Test
    void findFridgeByNumber_then_return_notNull() {

        when(jdbcTemplate.queryForObject(
                eq(sqlSelect),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
                )
        ).thenReturn(testFridge);

        Fridge actual = service.findFridgeByNumber(number);

        assertNotNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                );
    }

    @Test
    void findFridgeByNumber_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Fridge actual = service.findFridgeByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findFridgeByNumber_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception"){
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.findFridgeByNumber(number));
        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findFridgeByNumber_then_trows_EmptyResultDataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(0));

        Fridge actual = service.findFridgeByNumber(number);

        assertNull(actual);

        verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                (RowMapper<Fridge>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }
    @Test
    void updateFridge_then_return() {
        when(jdbcTemplate.update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateFridge.getNumber())
        )).thenReturn(updateFridge);

        Fridge actual = service.updateFridge(updateFridge);

        Assertions.assertEquals(actual,updateFridge);

        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateFridge.getNumber())
        );
    }

    @Test
    void updateFridge_then_throws_DataAccessException() {
        when(jdbcTemplate.update(
                eq(sqlUpdate),
                eq(updateFridge.getType()),
                eq(updateFridge.getDescription()),
                eq(updateFridge.getDiscount()),
                eq(updateFridge.getDefect()),
                eq(updateFridge.getPrice()),
                eq(updateFridge.getEnergy()),
                eq(updateFridge.getRegistered()),
                eq(updateFridge.getNumber())
        )).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.updateFridge(updateFridge));

        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(updateFridge.getNumber())
        );
    }

    @Test
    void deleteFridge_then_return() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        any(RowMapper.class),
                        eq(number)
                )
                ).thenReturn(testFridge);

        when(jdbcTemplate.update(
                        eq(sqlDelete),
                        eq(number)
                )
                ).thenReturn(1);

        Fridge actual = service.deleteFridge(number);

        Assertions.assertEquals(actual, testFridge);

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
   void deleteFridge_then_return_null() {

        when(jdbcTemplate.queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
                )
        ).thenReturn(null);

       Fridge actual = service.findFridgeByNumber(number);

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
    void deleteFridge_then_trows_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq(sqlSelect),
                        any(RowMapper.class),
                        eq(number)
                )
        ).thenReturn(testFridge);

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

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteFridge(number));

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
    void insertFridge_then_return() {
        when(jdbcTemplate.update(
                eq(sqlInsert),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        )
        ).thenReturn(testFridge);

        Fridge actual = service.insertFridge(testFridge);

        Assertions.assertEquals(actual, testFridge);

        verify(jdbcTemplate, new Times(1)).queryForObject(
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(sqlInsert),
                eq(testFridge.getNumber()),
                eq(testFridge.getType()),
                eq(testFridge.getDescription()),
                eq(testFridge.getDiscount()),
                eq(testFridge.getDefect()),
                eq(testFridge.getPrice()),
                eq(testFridge.getEnergy()),
                eq(testFridge.getRegistered())
        );
    }

    @Test
    void insertFridge_then_trows_DataAccessException() {
        when(jdbcTemplate.update(
                        eq(sqlInsert),
                        eq(testFridge.getNumber()),
                        eq(testFridge.getType()),
                        eq(testFridge.getDescription()),
                        eq(testFridge.getDiscount()),
                        eq(testFridge.getDefect()),
                        eq(testFridge.getPrice()),
                        eq(testFridge.getEnergy()),
                        eq(testFridge.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.insertFridge(testFridge));

        verify(jdbcTemplate, new Times(1)
        ).update(
                eq(sqlInsert),
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
                eq(sqlSelect),
                any(RowMapper.class),
                eq(number)
        );
    }

    @Test
    void readALL_then_return() {
        list.add(updateFridge);
        list.add(testFridge);

        when(jdbcTemplate.query(
                eq("SELECT * FROM FRIDGE"),
                any(RowMapper.class)
        )
        ).thenReturn(list);

        List<Fridge> actualList = service.readALL();

        Assertions.assertEquals(actualList, list);

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM FRIDGE"),
                any(RowMapper.class)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                        eq("SELECT * FROM FRIDGE"),
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
                eq("SELECT * FROM FRIDGE"),
                any(RowMapper.class)
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
        ).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteALL());

        verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM FRIDGE")
        );
    }
}