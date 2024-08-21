package by.ita.je.models;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Kettle {
    private String type;
    private String color;
    private Boolean isElectric;
    private Boolean isInduction;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;

    public Kettle(String type, String color, Boolean isElectric, Boolean isInduction, BigDecimal price, Integer number, Character energy, ZonedDateTime registered) {
        this.type = type;
        this.color = color;
        this.isElectric = isElectric;
        this.isInduction = isInduction;
        this.price = price;
        this.number = number;
        this.energy = energy;
        this.registered = registered;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public Boolean getElectric() {
        return isElectric;
    }

    public Boolean getInduction() {
        return isInduction;
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

    public static KettleBuilder builder() {
        return new KettleBuilder();
    }

    public static class KettleBuilder{
        private String type;
        private String color;
        private Boolean isElectric;
        private Boolean isInduction;
        private BigDecimal price;
        private Integer number;
        private Character energy;
        private ZonedDateTime registered;

        KettleBuilder(){
        }

        public KettleBuilder type (String type) {
            this.type = type;
            return this;
        }

        public KettleBuilder color (String color) {
            this.color = color;
            return this;
        }

        public KettleBuilder isElectric (Boolean isElectric) {
            this.isElectric = isElectric;
            return this;
        }

        public KettleBuilder isInduction (Boolean isInduction) {
            this.isInduction = isInduction;
            return this;
        }

        public KettleBuilder price (BigDecimal price) {
            this.price = price;
            return this;
        }

        public KettleBuilder number (Integer number) {
            this.number = number;
            return this;
        }
        public KettleBuilder energy (Character energy) {
            this.energy = energy;
            return this;
        }
        public KettleBuilder registered (ZonedDateTime registered) {
            this.registered = registered;
            return this;
        }

        public Kettle build(){
            return new Kettle(type, color, isElectric, isInduction, price, number, energy, registered);
        }

        @Override
        public String toString() {
            return "KettleBuilder{" +
                    "type='" + type + '\'' +
                    ", color='" + color + '\'' +
                    ", isElectric=" + isElectric +
                    ", isInduction=" + isInduction +
                    ", price=" + price +
                    ", number=" + number +
                    ", energy=" + energy +
                    ", registered=" + registered +
                    '}';
        }
    }
}
