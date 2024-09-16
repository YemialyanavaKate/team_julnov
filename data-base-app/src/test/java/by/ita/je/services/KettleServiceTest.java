package by.ita.je.services;

import by.ita.je.models.Kettle;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KettleServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private KettleService service;

    Random random = new Random();
    Integer number = random.nextInt(Integer.MAX_VALUE);
    Kettle testKettle = Kettle.builder()
            .number(number)
            .type("glass")
            .color("blue")
            .isElectric(false)
            .isInduction(false)
            .price(BigDecimal.valueOf(30.33))
            .energy('A')
            .registered(ZonedDateTime.parse("2023-12-26T20:28:33.213+02"))
            .build();
    Kettle updateKettle = Kettle.builder()
            .number(2)
            .type("glass")
            .color("pink")
            .isElectric(true)
            .isInduction(true)
            .price(BigDecimal.valueOf(122.22))
            .energy('A')
            .registered(ZonedDateTime.parse("2011-11-11T11:11:11+02"))
            .build();
    List<Kettle> list = new ArrayList<>();

    String sqlUpdate = "UPDATE KETTLE SET type = ?, color = ?, isElectric = ?, isInduction = ?, price = ?, energy = ?, registered = ? WHERE number = ?";

    String sqlInsert = "INSERT INTO KETTLE (number, type, color, isElectric, isInduction, price, energy, registered) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    @Test
    void findKettleByNumber_then_return_notNull() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        //eq((RowMapper<Kettle>)(resultSet, rowNum) -> mapRow(resultSet)),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(testKettle);

        Kettle actual = service.findKettleByNumber(number);

        assertNotNull(actual);

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findKettleByNumber_then_return_null() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenReturn(null);

        Kettle actual = service.findKettleByNumber(number);

        assertNull(actual);

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findKettleByNumber_then_trows_DataAccessException() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new DataAccessException("There exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class,() -> service.findKettleByNumber(number));

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void findKettleByNumber_then_trows_EmptyResultDataAccessException() {

        Mockito.when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                        eq(number)
                )
        ).thenThrow(new EmptyResultDataAccessException(1));

        Kettle actual = service.findKettleByNumber(number);

        assertNull(actual);

        Mockito.verify(
                jdbcTemplate, new Times(1)).queryForObject(
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                (RowMapper<Kettle>) argThat(argument -> argument instanceof RowMapper<?>),
                eq(number)
        );
    }

    @Test
    void updateKettle_then_return() {
        Mockito.when(jdbcTemplate.update(
                eq(sqlUpdate),
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
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        any(RowMapper.class),
                        eq(updateKettle.getNumber())
                )
        ).thenReturn(updateKettle);

        Kettle actual = service.updateKettle(updateKettle);

        Assertions.assertEquals(actual,updateKettle);
        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                any(RowMapper.class),
                eq(updateKettle.getNumber())
        );
    }

    @Test
    void updateKettle_then_throws_DataAccessException() {
        Mockito.when(jdbcTemplate.update(
                        eq(sqlUpdate),
                        eq(updateKettle.getType()),
                        eq(updateKettle.getColor()),
                        eq(updateKettle.getIsElectric()),
                        eq(updateKettle.getIsInduction()),
                        eq(updateKettle.getPrice()),
                        eq(updateKettle.getEnergy()),
                        eq(updateKettle.getRegistered()),
                        eq(updateKettle.getNumber())
                )
        ).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }
        );

        Assertions.assertThrows(DataAccessException.class, () -> service.updateKettle(updateKettle));
        verify(jdbcTemplate, new Times(1)).update(
                eq(sqlUpdate),
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
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                any(RowMapper.class),
                eq(updateKettle.getNumber())
        );
    }

    @Test
    void deleteKettle_then_return() {
        when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        any(RowMapper.class),
                        eq(number)
        )
        ).thenReturn(testKettle);

        when(jdbcTemplate.update(
                                eq("DELETE FROM KETTLE WHERE number = ?"),
                                eq(number)
                )
                )
                .thenReturn(1);

        Kettle actual = service.deleteKettle(number);

        Assertions.assertEquals(actual,testKettle);

        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                //eq((resultSet, rowNum) -> Kettle.builder().number(number).build()),
                any(RowMapper.class),
                eq(number)
        );

        Mockito.verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM KETTLE WHERE number = ?"),
                eq(number)
        );

    }

    @Test
    void deleteKettle_then_return_null() {

        when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        //(resultSet, rowNum) -> Kettle.builder().type("testType").build(),
                        any(RowMapper.class),
                        eq(number)
                )
        ).thenReturn(null);

        Kettle actual = service.deleteKettle(number);

        assertNull(actual);
        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        //eq((resultSet, rowNum) -> Kettle.builder().number(number).build()),
                        any(RowMapper.class),
                        eq(number)
                );

        Mockito.verify(jdbcTemplate, new Times(0)).update(
                eq("DELETE FROM KETTLE WHERE number = ?"),
                eq(number)
        );
    }

    @Test
    void deleteKettle_then_throws_DataAccessException() {

        when(jdbcTemplate.queryForObject(
                        eq("SELECT * FROM KETTLE WHERE number = ?"),
                        //(resultSet, rowNum) -> Kettle.builder().type("testType").build(),
                        any(RowMapper.class),
                        eq(number)
                )
        ).thenReturn(testKettle);

        when(jdbcTemplate.update(
                                eq("DELETE FROM KETTLE WHERE number = ?"),
                                eq(number)
                        )
                )
                .thenThrow(new DataAccessException("Boo") {
                    @Override
                    public String getMessage() {
                        return super.getMessage();
                    }
                }
                );

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteKettle(number));

        Mockito.verify(jdbcTemplate, new Times(1)).queryForObject(
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                //eq((resultSet, rowNum) -> Kettle.builder().number(number).build()),
                any(RowMapper.class),
                eq(number)
        );

        Mockito.verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM KETTLE WHERE number = ?"),
                eq(number)
        );
    }

    @Test
    void insertKettle_then_return() {
        when(jdbcTemplate.update(
                eq(sqlInsert),
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
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                any(RowMapper.class),
                eq(testKettle.getNumber())
        )
        ).thenReturn(testKettle);

        Kettle actual = service.insertKettle(testKettle);

        Assertions.assertEquals(actual,testKettle);

        verify(jdbcTemplate,new Times(1)).update(
                eq(sqlInsert),
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
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                any(RowMapper.class),
                eq(testKettle.getNumber())
        );
    }

    @Test
    void insertKettle_then_trows_DataAccessException() {
        when(jdbcTemplate.update(
                eq(sqlInsert),
                eq(testKettle.getNumber()),
                eq(testKettle.getType()),
                eq(testKettle.getColor()),
                eq(testKettle.getIsElectric()),
                eq(testKettle.getIsInduction()),
                eq(testKettle.getPrice()),
                eq(testKettle.getEnergy()),
                eq(testKettle.getRegistered())
                )
        ).thenThrow(new DataAccessException("There is DataAccessException"){
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });


        Assertions.assertThrows(DataAccessException.class, () -> service.insertKettle(testKettle));

        verify(jdbcTemplate,new Times(1)).update(
                eq(sqlInsert),
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
                eq("SELECT * FROM KETTLE WHERE number = ?"),
                any(RowMapper.class),
                eq(0)
        );
    }

    @Test
    void readALL_then_return() {
        list.add(updateKettle);
        list.add(testKettle);

        when(jdbcTemplate.query(
                eq("SELECT * FROM KETTLE"),
                any(RowMapper.class)
        )
        ).thenReturn(list);

        List<Kettle> actualList = service.readALL();

        Assertions.assertEquals(actualList,list);
        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM KETTLE"),
                any(RowMapper.class)
        );
    }

    @Test
    void readALL_then_throws_DataAccessException() {

        when(jdbcTemplate.query(
                eq("SELECT * FROM KETTLE"),
                any(RowMapper.class)
        )).thenThrow(new DataAccessException("There exception"){
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        Assertions.assertThrows(DataAccessException.class, () -> service.readALL());

        verify(jdbcTemplate, new Times(1)).query(
                eq("SELECT * FROM KETTLE"),
                any(RowMapper.class)
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
        ).thenThrow(new DataAccessException("There is exception") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }
        );

        Assertions.assertThrows(DataAccessException.class,() -> service.deleteAll());

        Mockito.verify(jdbcTemplate, new Times(1)).update(
                eq("DELETE FROM KETTLE")
        );
    }
}