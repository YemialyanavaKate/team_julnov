package by.ita.je.services;

import by.ita.je.models.Kettle;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class KettleServiceITTest extends TestUtils {
@Autowired
private KettleService kettleService;

    @Test
    public void contextTest(){
        Assertions.assertNotNull(kettleService);
    }
    @Test
    public void kettleByNumber_then_return_not_null(){
        Kettle kettleByNumber = kettleService.findKettleByNumber(1);
        Assertions.assertNotNull(kettleByNumber);
    }
    @Test
    public void kettleByNumber_then_return_correct_kettle(){
        Kettle kettleByNumber = kettleService.findKettleByNumber(2);
        Assertions.assertEquals("glass", kettleByNumber.getType());
        Assertions.assertEquals("notColor", kettleByNumber.getColor());
        Assertions.assertEquals(false, kettleByNumber.getIsElectric());
        Assertions.assertEquals(false, kettleByNumber.getIsInduction());
        Assertions.assertEquals(BigDecimal.valueOf(81), kettleByNumber.getPrice());
        Assertions.assertEquals('B', kettleByNumber.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2023-12-31T14:11:54+02"), kettleByNumber.getRegistered());
    }
    @Test
    public void kettleByNumber_then_trows_EmptyResultDataAccessException(){
        Kettle kettleByNumber = kettleService.findKettleByNumber(7);
        Assertions.assertNull(kettleByNumber);
    }

    @Test
    public void insert_then_return_correct_kettle(){
        Kettle testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        Kettle insertKettle = kettleService.insertKettle(testKettle);
        Assertions.assertEquals("glass", insertKettle.getType());
        Assertions.assertEquals("blue", insertKettle.getColor());
        Assertions.assertEquals(false, insertKettle.getIsElectric());
        Assertions.assertEquals(false, insertKettle.getIsInduction());
        Assertions.assertEquals(BigDecimal.valueOf(30), insertKettle.getPrice());
        Assertions.assertEquals('A', insertKettle.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2023-12-26T20:28:33.213+02"), insertKettle.getRegistered());
    }

    @Test
    public void update_then_return_correct_kettle(){
        Kettle updateKettleTest = Kettle.builder()
                .number(4)
                .type("glass")
                .color("pink")
                .isElectric(true)
                .isInduction(true)
                .price(BigDecimal.valueOf(122))
                .energy('A')
                .registered(ZonedDateTime.parse("2011-11-11T11:11:11+02"))
                .build();

        Kettle updateKettleActual = kettleService.updateKettle(updateKettleTest);
        Assertions.assertEquals("glass", updateKettleActual.getType());
        Assertions.assertEquals("pink", updateKettleActual.getColor());
        Assertions.assertEquals(true, updateKettleActual.getIsElectric());
        Assertions.assertEquals(true, updateKettleActual.getIsInduction());
        Assertions.assertEquals(BigDecimal.valueOf(122), updateKettleActual.getPrice());
        Assertions.assertEquals('A', updateKettleActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2011-11-11T11:11:11+02"), updateKettleActual.getRegistered());
    }

    @Test
    public void update_then_return_correct_kettle_after_insert(){
        Kettle testKettle = buildKettle("glass", "blue", false, false, BigDecimal.valueOf(30.33), 'A', ZonedDateTime.parse("2023-12-26T20:28:33.213+02"));

        kettleService.insertKettle(testKettle);
        Kettle updateKettleTest = Kettle.builder()
                .number(testKettle.getNumber())
                .type("glass")
                .color("pink")
                .isElectric(true)
                .isInduction(true)
                .price(BigDecimal.valueOf(122))
                .energy('A')
                .registered(ZonedDateTime.parse("2011-11-11T11:11:11+02"))
                .build();

        Kettle updateKettleActual = kettleService.updateKettle(updateKettleTest);
        Assertions.assertEquals("glass", updateKettleActual.getType());
        Assertions.assertEquals("pink", updateKettleActual.getColor());
        Assertions.assertEquals(true, updateKettleActual.getIsElectric());
        Assertions.assertEquals(true, updateKettleActual.getIsInduction());
        Assertions.assertEquals(BigDecimal.valueOf(122), updateKettleActual.getPrice());
        Assertions.assertEquals('A', updateKettleActual.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2011-11-11T11:11:11+02"), updateKettleActual.getRegistered());
    }

    @Test
    @Transactional
    public void delete_then_return(){
        Kettle deleteKettle = kettleService.deleteKettle(1);
        Assertions.assertEquals("steel", deleteKettle.getType());
        Assertions.assertEquals("silver", deleteKettle.getColor());
        Assertions.assertEquals(true, deleteKettle.getIsElectric());
        Assertions.assertEquals(false, deleteKettle.getIsInduction());
        Assertions.assertEquals(BigDecimal.valueOf(51), deleteKettle.getPrice());
        Assertions.assertEquals('A', deleteKettle.getEnergy());
        Assertions.assertEquals(ZonedDateTime.parse("2023-10-19T10:23:54+02"), deleteKettle.getRegistered());

    }

    @Test
    public void readAll_then_return(){
        List<Kettle> allKettle = kettleService.readALL();
        Assertions.assertNotNull(allKettle);
    }

    @Test
    @Transactional
    public void deleteAll_then_return() {
        kettleService.deleteAll();
        List<Kettle> allKettle = kettleService.readALL();
        Assertions.assertEquals(allKettle, Collections.emptyList());
    }
}