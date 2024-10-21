package by.ita.je.services;

import by.ita.je.models.Fridge;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FridgeServiceITTest extends TestUtils {
    @Autowired
    private FridgeService fridgeService;

    @Test
    public void contextTest() {
        Assertions.assertNotNull(fridgeService);
    }

    @Test
    public void fridgeByNumber_then_return_not_null() {
        Fridge fridgeByNumber = fridgeService.findFridgeByNumber(2);
        Assertions.assertNotNull(fridgeByNumber);
    }

    @Test
    public void fridgeByNumber_then_return_correct_fridge() {
        Fridge fridgeByNumber = fridgeService.findFridgeByNumber(2);
        Assertions.assertEquals("noFrost", fridgeByNumber.getType());
        Assertions.assertEquals("OK fridge", fridgeByNumber.getDescription());
        Assertions.assertEquals(false, fridgeByNumber.getDiscount());
        Assertions.assertEquals(true, fridgeByNumber.getDefect());
        Assertions.assertEquals(BigDecimal.valueOf(2501), fridgeByNumber.getPrice());
        Assertions.assertEquals('B', fridgeByNumber.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2024-08-19T11:23:54+03:00[Europe/Moscow]"), fridgeByNumber.getRegistered());
    }

    @Test
    public void fridgeByNumber_then_trows_EmptyResultDataAccessException() {
        Fridge fridgeByNumber = fridgeService.findFridgeByNumber(7);
        Assertions.assertNull(fridgeByNumber);
    }

    @Transactional
    @Test
    public void insert_then_return_correct_fridge() {

        Fridge insertFridge = fridgeService.insertFridge();
        Assertions.assertEquals("Samsung", insertFridge.getType());
        Assertions.assertEquals("The best", insertFridge.getDescription());
        Assertions.assertEquals(false, insertFridge.getDiscount());
        Assertions.assertEquals(false, insertFridge.getDefect());
        Assertions.assertEquals(BigDecimal.valueOf(3000.99), insertFridge.getPrice());
        Assertions.assertEquals('A', insertFridge.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2023-05-13T07:50:54+02"), insertFridge.getRegistered());
    }

    @Test
    @Transactional
    public void update_then_return_correct_fridge() {
        Fridge updateFridgeTest = Fridge.builder()
                .number(4)
                .type("slim")
                .description("So so")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(1000.8))
                .energy('A')
                .registered(ZonedDateTime.parse("2019-11-19T19:19:54+02"))
                .build();

        Fridge updateFridgeActual = fridgeService.updateFridge(updateFridgeTest);
        Assertions.assertEquals("slim", updateFridgeActual.getType());
        Assertions.assertEquals("So so", updateFridgeActual.getDescription());
        Assertions.assertEquals(true, updateFridgeActual.getDiscount());
        Assertions.assertEquals(true, updateFridgeActual.getDefect());
        Assertions.assertEquals(BigDecimal.valueOf(1000.8), updateFridgeActual.getPrice());
        Assertions.assertEquals('A', updateFridgeActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2019-11-19T19:19:54+02"), updateFridgeActual.getRegistered());
    }

    @Test
    public void update_then_return_correct_fridge_after_insert() {
        Fridge testFridge = new Fridge("Samsung", "The best", false, false, BigDecimal.valueOf(3000.99), 111, 'A', ZonedDateTime.parse("2023-12-23T10:23:54+02"), null, null);

        fridgeService.insertFridge();
        Fridge updateFridgeTest = Fridge.builder()
                .number(testFridge.getNumber())
                .type("slim")
                .description("So so")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(1000.8))
                .energy('A')
                .registered(ZonedDateTime.parse("2019-11-19T19:19:54+02"))
                .build();

        Fridge updateFridgeActual = fridgeService.updateFridge(updateFridgeTest);
        Assertions.assertEquals("slim", updateFridgeActual.getType());
        Assertions.assertEquals("So so", updateFridgeActual.getDescription());
        Assertions.assertEquals(true, updateFridgeActual.getDiscount());
        Assertions.assertEquals(true, updateFridgeActual.getDefect());
        Assertions.assertEquals(BigDecimal.valueOf(1000.8), updateFridgeActual.getPrice());
        Assertions.assertEquals('A', updateFridgeActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2019-11-19T19:19:54+02"), updateFridgeActual.getRegistered());
    }

    @Test
    @Transactional
    public void delete_then_return() {
        Fridge deleteFridge = fridgeService.deleteFridge(1);
        Assertions.assertEquals("integral", deleteFridge.getType());
        Assertions.assertEquals("good fridge", deleteFridge.getDescription());
        Assertions.assertEquals(true, deleteFridge.getDiscount());
        Assertions.assertEquals(false, deleteFridge.getDefect());
        Assertions.assertEquals(BigDecimal.valueOf(2001), deleteFridge.getPrice());
        Assertions.assertEquals('A', deleteFridge.getEnergy());
    }

    @Test
    public void readAll_then_return() {
        List<Fridge> allFridge = fridgeService.readALL();
        Assertions.assertNotNull(allFridge);
    }

    @Test
    @Transactional
    public void deleteAll_then_return() {
        fridgeService.deleteALL();
        List<Fridge> allFridge = fridgeService.readALL();
        Assertions.assertEquals(allFridge, Collections.emptyList());
    }
}