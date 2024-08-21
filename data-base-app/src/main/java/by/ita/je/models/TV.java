package by.ita.je.models;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TV {
    private String type;
    private String brand;
    private Boolean discount;
    private Integer diagonal;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;

    public TV(String type, String brand, Boolean discount, Integer diagonal, BigDecimal price, Integer number, Character energy, ZonedDateTime registered) {
        this.type = type;
        this.brand = brand;
        this.discount = discount;
        this.diagonal = diagonal;
        this.price = price;
        this.number = number;
        this.energy = energy;
        this.registered = registered;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public Integer getDiagonal() {
        return diagonal;
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

    public static TVBuilder builder() {
        return new TVBuilder();
    }

    public static class TVBuilder {
        private String type;
        private String brand;
        private Boolean discount;
        private Integer diagonal;
        private BigDecimal price;
        private Integer number;
        private Character energy;
        private ZonedDateTime registered;

        TVBuilder(){
        }

        public TVBuilder type(String type) {
            this.type = type;
            return this;
        }

        public TVBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public TVBuilder discount (Boolean discount) {
            this.discount = discount;
            return this;
        }

        public TVBuilder numberModes(Integer diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public TVBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TVBuilder number(Integer number) {
            this.number = number;
            return this;
        }
        public TVBuilder energy (Character energy) {
            this.energy = energy;
            return this;
        }
        public TVBuilder registered (ZonedDateTime registered) {
            this.registered = registered;
            return this;
        }

        public TV build(){
            return new TV(type, brand, discount, diagonal, price, number, energy, registered);
        }
    }

    @Override
    public String toString() {
        return "TV{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", discount=" + discount +
                ", diagonal=" + diagonal +
                ", price=" + price +
                ", number=" + number +
                ", energy=" + energy +
                ", registered=" + registered +
                '}';
    }
}
