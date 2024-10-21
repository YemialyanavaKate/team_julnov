package by.ita.je.services;

import by.ita.je.models.Multicooker;
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
class MulticookerServiceITTest extends TestUtils {
    @Autowired
    private MulticookerService multicookerService;

    @Test
    public void contextTest() {
        Assertions.assertNotNull(multicookerService);
    }

    @Test
    public void multicookerByNumber_then_return_not_null() {
        Multicooker multicookerByNumber = multicookerService.findMulticookerByNumber(2);
        Assertions.assertNotNull(multicookerByNumber);
    }

    @Test
    public void multicookerByNumber_then_return_correct_multicooker() {
        Multicooker multicookerByNumber = multicookerService.findMulticookerByNumber(2);
        Assertions.assertEquals("NonRemovablePanels", multicookerByNumber.getType());
        Assertions.assertEquals("for sweet", multicookerByNumber.getDescription());
        Assertions.assertEquals(false, multicookerByNumber.getIsTouchScreen());
        Assertions.assertEquals(20001, multicookerByNumber.getNumberModes());
        Assertions.assertEquals(BigDecimal.valueOf(100), multicookerByNumber.getPrice());
        Assertions.assertEquals('B', multicookerByNumber.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2024-05-12T00:23:54+03:00[Europe/Moscow]"), multicookerByNumber.getRegistered());
    }

    @Test
    public void multicookerByNumber_then_trows_EmptyResultDataAccessException() {
        Multicooker multicookerByNumber = multicookerService.findMulticookerByNumber(7);
        Assertions.assertNull(multicookerByNumber);
    }

    @Test
    @Transactional
    public void insert_then_return_correct_multicooker() {
        Multicooker insertMulticooker = multicookerService.insertMulticooker();
        Assertions.assertEquals("Midea", insertMulticooker.getType());
        Assertions.assertEquals("So so", insertMulticooker.getDescription());
        Assertions.assertEquals(true, insertMulticooker.getIsTouchScreen());
        Assertions.assertEquals(91, insertMulticooker.getNumberModes());
        Assertions.assertEquals(BigDecimal.valueOf(1112), insertMulticooker.getPrice());
        Assertions.assertEquals('B', insertMulticooker.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2024-01-01T10:23:54+02"), insertMulticooker.getRegistered());
    }

    @Test
    @Transactional
    public void update_then_return_correct_multicooker() {
        Multicooker updateMulticookerTest = Multicooker.builder()
                .number(4)
                .type("NonRemovablePanels")
                .description("for sweet")
                .isTouchScreen(true)
                .numberModes(5000)
                .price(BigDecimal.valueOf(78.5))
                .energy('A')
                .registered(ZonedDateTime.parse("2023-12-29T10:23:54+02"))
                .fridge(null)
                .build();

        Multicooker updateMulticookerActual = multicookerService.updateMulticooker(updateMulticookerTest);
        Assertions.assertEquals("Tefal", updateMulticookerActual.getType());
        Assertions.assertEquals("for sweet", updateMulticookerActual.getDescription());
        Assertions.assertEquals(true, updateMulticookerActual.getIsTouchScreen());
        Assertions.assertEquals(5000, updateMulticookerActual.getNumberModes());
        Assertions.assertEquals(BigDecimal.valueOf(1000.5), updateMulticookerActual.getPrice());
        Assertions.assertEquals('A', updateMulticookerActual.getEnergy());
    }

    @Test
    @Transactional
    public void update_then_return_correct_multicooker_after_insert() {
        Multicooker testMulticooker = multicookerService.insertMulticooker();

        Multicooker updateMulticookerActual = multicookerService.updateMulticooker(testMulticooker);
        Assertions.assertEquals("Tefal", updateMulticookerActual.getType());
        Assertions.assertEquals("So so", updateMulticookerActual.getDescription());
        Assertions.assertEquals(true, updateMulticookerActual.getIsTouchScreen());
        Assertions.assertEquals(91, updateMulticookerActual.getNumberModes());
        Assertions.assertEquals(BigDecimal.valueOf(1000.5), updateMulticookerActual.getPrice());
        Assertions.assertEquals('B', updateMulticookerActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2024-01-01T10:23:54+02"), updateMulticookerActual.getRegistered());
    }

    @Test
    @Transactional
    public void delete_then_return() {
        Multicooker deleteMulticooker = multicookerService.deleteMulticooker(1);
        Assertions.assertEquals("RemovablePanels", deleteMulticooker.getType());
        Assertions.assertEquals("for breakfast", deleteMulticooker.getDescription());
        Assertions.assertEquals(true, deleteMulticooker.getIsTouchScreen());
        Assertions.assertEquals(15001, deleteMulticooker.getNumberModes());
        Assertions.assertEquals(BigDecimal.valueOf(51), deleteMulticooker.getPrice());
        Assertions.assertEquals('A', deleteMulticooker.getEnergy());
    }

    @Test
    public void readAll_then_return() {
        List<Multicooker> allMulticooker = multicookerService.readALL();
        Assertions.assertNotNull(allMulticooker);
    }

    @Test
    @Transactional
    public void deleteAll_then_return() {
        multicookerService.deleteALL();
        List<Multicooker> allMulticooker = multicookerService.readALL();
        Assertions.assertEquals(allMulticooker, Collections.emptyList());
    }
}