package by.ita.je.services.utils;

import by.ita.je.models.Fridge;
import by.ita.je.models.Kettle;
import by.ita.je.models.Multicooker;
import by.ita.je.models.TV;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

public abstract class TestUtils {
    public static final String SQL_SELECT_FRIDGE = "SELECT * FROM FRIDGE WHERE number = ?";
    public static final String SQL_UPDATE_FRIDGE = "UPDATE FRIDGE SET type = ?, description = ?, discount = ?, defect = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    public static final String SQL_DELETE_FRIDGE = "DELETE FROM FRIDGE WHERE number = ?";
    public static final String SQL_INSERT_FRIDGE = "INSERT INTO FRIDGE(number, type, description, discount, defect, price, energy, registered) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String SQL_SELECT_KETTLE = "SELECT * FROM KETTLE WHERE number = ?";
    public static final String SQL_UPDATE_KETTLE = "UPDATE KETTLE SET type = ?, color = ?, isElectric = ?, isInduction = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    public static final String SQL_DELETE_KETTLE = "DELETE FROM KETTLE WHERE number = ?";
    public static final String SQL_INSERT_KETTLE = "INSERT INTO KETTLE (number, type, color, isElectric, isInduction, price, energy, registered) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT_MULTICOOKER = "SELECT * FROM MULTICOOKER WHERE number = ?";
    public static final String SQL_UPDATE_MULTICOOKER = "UPDATE MULTICOOKER SET type = ?, description = ?, isTouchScreen = ?, numberModes = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    public static final String SQL_DELETE_MULTICOOKER = "DELETE FROM MULTICOOKER WHERE number = ?";
    public static final String SQL_INSERT_MULTICOOKER = "INSERT INTO MULTICOOKER (number, type, description, isTouchScreen, numberModes, price, energy, registered)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String SQL_SELECT_TV = "SELECT * FROM TV WHERE number = ?";
    public static final String SQL_UPDATE_TV = "UPDATE TV SET type = ?, brand = ?, discount = ?, diagonal = ?, price = ?, energy = ?, registered = ? WHERE number = ?";
    public static final String SQL_DELETE_TV = "DELETE FROM TV WHERE number = ?";
    public static final String SQL_INSERT_TV = "INSERT INTO TV (number, type, brand, discount, diagonal, price, energy, registered)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    protected Integer number;
    protected Fridge testFridge;
    protected Fridge updateFridge;
    protected List<Fridge> listFridge;
    protected Kettle testKettle;
    protected Kettle updateKettle;
    protected List<Kettle> listKettle;
    protected Multicooker testMulticooker;
    protected Multicooker updateMulticooker;
    protected List<Multicooker> listMulticooker;
    protected TV testTV;
    protected TV updateTV;
    protected List<TV> listTV;
    protected Fridge buildFridge(String type, String description, Boolean discount, Boolean defect, BigDecimal price, Character energy, ZonedDateTime registered) {
        Random random = new Random();
        number = random.nextInt(Integer.MAX_VALUE);
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
        number = random.nextInt(Integer.MAX_VALUE);
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
        number = random.nextInt(Integer.MAX_VALUE);
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
        number = random.nextInt(Integer.MAX_VALUE);
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
