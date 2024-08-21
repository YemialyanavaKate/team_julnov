package by.ita.je.models;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Fridge {
    private String type;
    private String description;
    private Boolean discount;
    private Boolean defect;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;

    public Fridge(String type, String description, Boolean discount, Boolean defect, BigDecimal price, Integer number, Character energy, ZonedDateTime registered) {
        this.type = type;
        this.description = description;
        this.discount = discount;
        this.defect = defect;
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

    public Boolean getDiscount() {
        return discount;
    }

    public Boolean getDefect() {
        return defect;
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

    public static FridgeBuilder builder() {
        return new FridgeBuilder();
    }

    public static class FridgeBuilder {

        private String type;
        private String description;
        private Boolean discount;
        private Boolean defect;
        private BigDecimal price;
        private Integer number;
        private Character energy;
        private ZonedDateTime registered;

        FridgeBuilder() {
        }

        public FridgeBuilder type(String type) {
            this.type = type;
            return this;
        }

        public FridgeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FridgeBuilder discount(Boolean discount) {
            this.discount = discount;
            return this;
        }

        public FridgeBuilder defect(Boolean defect) {
            this.defect = defect;
            return this;
        }

        public FridgeBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public FridgeBuilder number(Integer number) {
            this.number = number;
            return this;
        }
        public FridgeBuilder energy (Character energy) {
            this.energy = energy;
            return this;
        }
        public FridgeBuilder registered (ZonedDateTime registered) {
            this.registered = registered;
            return this;
        }

        public Fridge build(){
            return new Fridge(type, description, discount, defect, price, number, energy, registered);
        }
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", defect=" + defect +
                ", price=" + price +
                ", number=" + number +
                ", energy=" + energy +
                ", registered=" + registered +
                '}';
    }
}
