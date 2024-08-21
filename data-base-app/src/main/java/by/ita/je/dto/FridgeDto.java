package by.ita.je.dto;

import java.math.BigDecimal;

public class FridgeDto {
    private String type;
    private String description;
    private Boolean discount;
    private Boolean defect;
    private BigDecimal price;
    private Integer number;

    public FridgeDto(String type, String description, Boolean discount, Boolean defect, BigDecimal price, Integer number) {
        this.type = type;
        this.description = description;
        this.discount = discount;
        this.defect = defect;
        this.price = price;
        this.number = number;
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

    public static FridgeDtoBuilder builder() {
        return new FridgeDtoBuilder();
    }

    public static class FridgeDtoBuilder {
        
            private String type;
            private String description;
            private Boolean discount;
            private Boolean defect;
            private BigDecimal price;
            private Integer number;

            FridgeDtoBuilder() {
            }

            public FridgeDtoBuilder type(String type) {
                this.type = type;
                return this;
            }

            public FridgeDtoBuilder description(String description) {
                this.description = description;
                return this;
            }

            public FridgeDtoBuilder discount(Boolean discount) {
                this.discount = discount;
                return this;
            }

            public FridgeDtoBuilder defect(Boolean defect) {
                this.defect = defect;
                return this;
            }

            public FridgeDtoBuilder price(BigDecimal price) {
                this.price = price;
                return this;
            }

            public FridgeDtoBuilder number(Integer number) {
                this.number = number;
                return this;
            }

            public FridgeDto build(){
                return new FridgeDto(type, description, discount, defect, price, number);
            }

        }

    @Override
    public String toString() {
        return "FridgeDto{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", defect=" + defect +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}





