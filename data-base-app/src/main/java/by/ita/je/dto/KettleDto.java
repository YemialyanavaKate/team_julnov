package by.ita.je.dto;

import java.math.BigDecimal;

public class KettleDto {
    private String type;
    private String color;
    private Boolean isElectric;
    private Boolean isInduction;
    private BigDecimal price;
    private Integer number;

    public KettleDto(String type, String color, Boolean isElectric, Boolean isInduction, BigDecimal price, Integer number) {
        this.type = type;
        this.color = color;
        this.isElectric = isElectric;
        this.isInduction = isInduction;
        this.price = price;
        this.number = number;
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

    public static KettleDtoBuilder builder() {
        return new KettleDtoBuilder();
    }

    public static class KettleDtoBuilder{
        private String type;
        private String color;
        private Boolean isElectric;
        private Boolean isInduction;
        private BigDecimal price;
        private Integer number;

        KettleDtoBuilder(){
        }

        public KettleDtoBuilder type (String type) {
            this.type = type;
            return this;
        }

        public KettleDtoBuilder color (String color) {
            this.color = color;
            return this;
        }

        public KettleDtoBuilder isElectric (Boolean isElectric) {
            this.isElectric = isElectric;
            return this;
        }

        public KettleDtoBuilder isInduction (Boolean isInduction) {
            this.isInduction = isInduction;
            return this;
        }

        public KettleDtoBuilder price (BigDecimal price) {
            this.price = price;
            return this;
        }

        public KettleDtoBuilder number (Integer number) {
            this.number = number;
            return this;
        }

        public KettleDto build(){
            return new KettleDto(type, color, isElectric, isInduction, price, number);
        }

        @Override
        public String toString() {
            return "KettleDtoBuilder{" +
                    "type='" + type + '\'' +
                    ", color='" + color + '\'' +
                    ", isElectric=" + isElectric +
                    ", isInduction=" + isInduction +
                    ", price=" + price +
                    ", number=" + number +
                    '}';
        }
    }
}
