package by.ita.je.models;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Multicooker {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;

    public Multicooker(String type, String description, Boolean isTouchScreen, Integer numberModes, BigDecimal price, Integer number, Character energy, ZonedDateTime registered) {
        this.type = type;
        this.description = description;
        this.isTouchScreen = isTouchScreen;
        this.numberModes = numberModes;
        this.price = price;
        this.number = number;
        this.energy = energy;
        this.registered = registered;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getTouchScreen() {
        return isTouchScreen;
    }

    public Integer getNumberModes() {
        return numberModes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getNumber() {
        return number;
    }

    public Character getEnergy() {
        return energy;
    }

    public ZonedDateTime getRegistered() {
        return registered;
    }

    public static MulticookerBuilder builder() {
        return new MulticookerBuilder();
    }

    public static class MulticookerBuilder {

        private String type;
        private String description;
        private Boolean isTouchScreen;
        private Integer numberModes;
        private BigDecimal price;
        private Integer number;
        private Character energy;
        private ZonedDateTime registered;

        MulticookerBuilder(){
        }

        public MulticookerBuilder type(String type) {
            this.type = type;
            return this;
        }

        public MulticookerBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MulticookerBuilder isTouchScreen(Boolean isTouchScreen) {
            this.isTouchScreen = isTouchScreen;
            return this;
        }

        public MulticookerBuilder numberModes(Integer numberModes) {
            this.numberModes = numberModes;
            return this;
        }

        public MulticookerBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public MulticookerBuilder number(Integer number) {
            this.number = number;
            return this;
        }
        public MulticookerBuilder energy (Character energy) {
            this.energy = energy;
            return this;
        }
        public MulticookerBuilder registered (ZonedDateTime registered) {
            this.registered = registered;
            return this;
        }

        public Multicooker build(){
            return new Multicooker(type, description, isTouchScreen, numberModes, price, number, energy, registered);
        }

        @Override
        public String toString() {
            return "MulticookerBuilder{" +
                    "type='" + type + '\'' +
                    ", description='" + description + '\'' +
                    ", isTouchScreen=" + isTouchScreen +
                    ", numberModes=" + numberModes +
                    ", price=" + price +
                    ", number=" + number +
                    ", energy=" + energy +
                    ", registered=" + registered +
                    '}';
        }
    }
}
