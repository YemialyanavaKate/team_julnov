package by.ita.je.services;

import by.ita.je.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BusinessServiceIT {
    @Autowired
    private BusinessService businessService;

    @Test
    public void contextTest() {
        Assertions.assertNotNull(businessService);
    }

    @Test
    public void create_then_return_not_null() {

        Fridge testfridge = Fridge.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .build();

        Fridge fridge = businessService.create(testfridge);
        Assertions.assertNotNull(fridge);
    }

    @Test
    public void create_then_return_correct_fridge() {

        Fridge testfridge = Fridge.builder()
                .type("Samsung")
                .description("The best of the World")
                .discount(true)
                .defect(true)
                .price(BigDecimal.valueOf(3000.33))
                .build();
        Fridge fridge = businessService.create(testfridge);
        Assertions.assertEquals("Samsung", fridge.getType());
        Assertions.assertEquals("The best of the World", fridge.getDescription());
        Assertions.assertEquals(true, fridge.getDiscount());
        Assertions.assertEquals(true, fridge.getDefect());
        Assertions.assertEquals(BigDecimal.valueOf(3000.33), fridge.getPrice());
    }

    @Test
    public void findFridgePlusKettleAndMulticooker_then_return_correct_fridge() {

        Kettle kettle = Kettle.builder()
                .number(5)
                .type("glass")
                .color("blue")
                .isElectric(false)
                .isInduction(false)
                .price(BigDecimal.valueOf(30.33)).energy('A')
                .registered(ZonedDateTime.parse("2023-12-26T20:28:33.213+02"))
                .build();

        Multicooker multicooker = Multicooker.builder()
                .number(5)
                .type("Midea")
                .description("So so")
                .isTouchScreen(true)
                .numberModes(91)
                .price(BigDecimal.valueOf(1112))
                .energy('B')
                .registered(ZonedDateTime.parse("2024-01-01T10:23:54+02"))
                .build();
        List<Multicooker> multicookers = new ArrayList<>();
        multicookers.add(multicooker);

        Fridge testfridge = Fridge.builder()
                .Multicookers(multicookers)
                .kettle(kettle)
                .build();



        Fridge testFridgeWithEntity = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .Multicookers(multicookers)
                .kettle(kettle)
                .build();

        Fridge actualfridge = businessService.findFridgePlusKettleAndMulticooker(1, testfridge);
        Assertions.assertEquals(actualfridge, testFridgeWithEntity);
    }

    @Test
    public void findFridgePlusTVAndCountryByConditional_then_then_return_correct_fridge() {

        TV tv = TV.builder()
                .price(BigDecimal.valueOf(2001))
                .country(Country.BELARUS)
                .build();
        List<TV> tvList = new ArrayList<>();
        tvList.add(tv);

        Kettle kettle = Kettle.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150))
                .number(4)
                .listTV(tvList)
                .build();

        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .Multicookers(Collections.emptyList())
                .kettle(kettle)
                .build();

        Fridge actualfridge = businessService.findFridgePlusTVAndCountryByConditional(1, 55);
        Assertions.assertEquals(actualfridge, testfridge);
    }

    @Test
    public void findFridge_then_then_return_correct_fridge() {

        Kettle kettle = Kettle.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150))
                .number(4)
                .listTV(null)
                .build();

        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .Multicookers(Collections.emptyList())
                .kettle(kettle)
                .build();

        Fridge actualfridge = businessService.findFridge(1);
        Assertions.assertEquals(actualfridge, testfridge);
    }

    @Test
    public void delete_then_return() {
        Kettle kettle = Kettle.builder()
                .type("steel")
                .color("white")
                .isElectric(true)
                .isInduction(false)
                .price(BigDecimal.valueOf(150))
                .number(4)
                .listTV(null)
                .build();

        Fridge testfridge = Fridge.builder()
                .type("integral")
                .description("good fridge")
                .discount(true)
                .defect(false)
                .price(BigDecimal.valueOf(2001))
                .number(1)
                .Multicookers(Collections.emptyList())
                .kettle(kettle)
                .build();

        Fridge deletefridge = businessService.findFridgeAndDelete(1);
        Assertions.assertEquals(deletefridge, testfridge);

    }
}
