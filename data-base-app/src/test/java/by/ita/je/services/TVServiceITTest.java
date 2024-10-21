package by.ita.je.services;

import by.ita.je.models.TV;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TVServiceITTest extends TestUtils {
    @Autowired
    private TVService tvService;

    @Test
    public void contextTest() {
        Assertions.assertNotNull(tvService);
    }

    @Test
    public void tvByNumber_then_return_not_null() {
        TV tvByNumber = tvService.findTVByNumber(2);
        Assertions.assertNotNull(tvByNumber);
    }

    @Test
    public void tvByNumber_then_return_correct_tv() {
        TV tvByNumber = tvService.findTVByNumber(2);
        Assertions.assertEquals("LED", tvByNumber.getType());
        Assertions.assertEquals("Samsung", tvByNumber.getBrand());
        Assertions.assertEquals(false, tvByNumber.getDiscount());
        Assertions.assertEquals(38, tvByNumber.getDiagonal());
        Assertions.assertEquals(BigDecimal.valueOf(4001), tvByNumber.getPrice());
        Assertions.assertEquals('B', tvByNumber.getEnergy());
    }

    @Test
    public void tvByNumber_then_trows_EmptyResultDataAccessException() {
        TV tvByNumber = tvService.findTVByNumber(7);
        Assertions.assertNull(tvByNumber);
    }

    @Test
    @Transactional
    public void insert_then_return_correct_tv() {

        TV insertTV = tvService.insertTV();
        Assertions.assertEquals("TV", insertTV.getType());
        Assertions.assertEquals("LG", insertTV.getBrand());
        Assertions.assertEquals(true, insertTV.getDiscount());
        Assertions.assertEquals(15, insertTV.getDiagonal());
        Assertions.assertEquals(BigDecimal.valueOf(1000.5), insertTV.getPrice());
        Assertions.assertEquals('A', insertTV.getEnergy());
    }

    @Test
    public void update_then_return_correct_tv() {
        TV updateTVTest = TV.builder()
                .number(4)
                .type("TV")
                .brand("Horisont")
                .discount(true)
                .diagonal(32)
                .price(BigDecimal.valueOf(1222.8))
                .energy('B')
                .registered(ZonedDateTime.parse("2024-01-01T10:23:54+02", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .kettles(null)
                .country(null)
                .build();

        TV updateTVActual = tvService.updateTV(updateTVTest);
        Assertions.assertEquals("TV", updateTVActual.getType());
        Assertions.assertEquals("Horisont", updateTVActual.getBrand());
        Assertions.assertEquals(true, updateTVActual.getDiscount());
        Assertions.assertEquals(32, updateTVActual.getDiagonal());
        Assertions.assertEquals(BigDecimal.valueOf(1222.8), updateTVActual.getPrice());
        Assertions.assertEquals('B', updateTVActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2024-01-01T10:23:54+02"), updateTVActual.getRegistered());
    }

    @Test
    @Transactional
    public void update_then_return_correct_tv_after_insert() {
        TV testTV = buildTV("TV", "LG", false, 15, BigDecimal.valueOf(1000.5), 'A', ZonedDateTime.parse("2023-12-23T10:23:54+02"));

        tvService.insertTV();
        TV updateTVTest = TV.builder()
                .number(testTV.getNumber())
                .type("TV")
                .brand("Horisont")
                .discount(true)
                .diagonal(32)
                .price(BigDecimal.valueOf(1222.8))
                .energy('B')
                .registered(ZonedDateTime.parse("2024-01-01T10:23:54+02"))
                .build();

        TV updateTVActual = tvService.updateTV(updateTVTest);
        Assertions.assertEquals("TV", updateTVActual.getType());
        Assertions.assertEquals("Horisont", updateTVActual.getBrand());
        Assertions.assertEquals(true, updateTVActual.getDiscount());
        Assertions.assertEquals(32, updateTVActual.getDiagonal());
        Assertions.assertEquals(BigDecimal.valueOf(1222.8), updateTVActual.getPrice());
        Assertions.assertEquals('B', updateTVActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2024-01-01T10:23:54+02"), updateTVActual.getRegistered());
    }

    @Test
    @Transactional
    public void delete_then_return() {
        TV deleteTV = tvService.deleteTV(1);
        Assertions.assertEquals("LCD", deleteTV.getType());
        Assertions.assertEquals("LG", deleteTV.getBrand());
        Assertions.assertEquals(true, deleteTV.getDiscount());
        Assertions.assertEquals(40, deleteTV.getDiagonal());
        Assertions.assertEquals(BigDecimal.valueOf(5001), deleteTV.getPrice());
        Assertions.assertEquals('A', deleteTV.getEnergy());

    }

    @Test
    public void readAll_then_return() {
        List<TV> allTV = tvService.readALL();
        Assertions.assertNotNull(allTV);
    }

    @Test
    @Transactional
    public void deleteAll_then_return() {
        tvService.deleteAll();
        List<TV> allTV = tvService.readALL();
        Assertions.assertEquals(allTV, Collections.emptyList());
    }
}