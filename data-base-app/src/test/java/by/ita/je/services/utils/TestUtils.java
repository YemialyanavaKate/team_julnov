package by.ita.je.services.utils;

import by.ita.je.models.Fridge;
import by.ita.je.models.Kettle;
import by.ita.je.models.Multicooker;
import by.ita.je.models.TV;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Random;

public abstract class TestUtils {

    protected Fridge buildFridge(String type, String description, Boolean discount, Boolean defect, BigDecimal price, Character energy, ZonedDateTime registered) {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        Fridge fridge = Fridge.builder()
                .number(number)
                .type(type)
                .description(description)
                .discount(discount)
                .defect(defect)
                .price(price)
                .energy(energy)
                .registered(registered)
                .build();
       return fridge;
    }

    protected Kettle buildKettle(String type, String color, Boolean isElectric, Boolean isInduction, BigDecimal price, Character energy, ZonedDateTime registered) {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        Kettle kettle = Kettle.builder()
                .number(number)
                .type(type)
                .color(color)
                .isElectric(isElectric)
                .isInduction(isInduction)
                .price(price)
                .energy(energy)
                .registered(registered)
                .build();
        return kettle;
    }

    protected Multicooker buildMulticooker(String type, String description, Boolean isTouchScreen, Integer numberModes, BigDecimal price, Character energy, ZonedDateTime registered) {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        Multicooker multicooker = Multicooker.builder()
                .number(number)
                .type(type)
                .description(description)
                .isTouchScreen(isTouchScreen)
                .numberModes(numberModes)
                .price(price)
                .energy(energy)
                .registered(registered)
                .build();
        return multicooker;
    }

    protected TV buildTV(String type, String brand, Boolean discount, Integer diagonal, BigDecimal price, Character energy, ZonedDateTime registered) {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);
        TV tv = TV.builder()
                .number(number)
                .type(type)
                .brand(brand)
                .discount(discount)
                .diagonal(diagonal)
                .price(price)
                .energy(energy)
                .registered(registered)
                .build();
        return tv;
    }
}
