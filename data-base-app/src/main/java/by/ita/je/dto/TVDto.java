package by.ita.je.dto;

import java.math.BigDecimal;

public class TVDto {
    private String type;
    private String brand;
    private Boolean discount;
    private Integer diagonal;
    private BigDecimal price;
    private Integer number;

    public TVDto(String type, String brand, Boolean discount, Integer diagonal, BigDecimal price, Integer number) {
        this.type = type;
        this.brand = brand;
        this.discount = discount;
        this.diagonal = diagonal;
        this.price = price;
        this.number = number;
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

    public static TVDtoBuilder builder() {
        return new TVDtoBuilder();
    }

    public static class TVDtoBuilder {
        private String type;
        private String brand;
        private Boolean discount;
        private Integer diagonal;
        private BigDecimal price;
        private Integer number;
        TVDtoBuilder(){

        }
        public TVDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public TVDtoBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public TVDtoBuilder discount (Boolean discount) {
            this.discount = discount;
            return this;
        }

        public TVDtoBuilder diagonal(Integer diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public TVDtoBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TVDtoBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public TVDto build(){
            return new TVDto(type, brand, discount, diagonal, price, number);
        }
    }

    @Override
    public String toString() {
        return "TVDto{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", discount=" + discount +
                ", diagonal=" + diagonal +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
