package by.ita.je.dto;

import java.math.BigDecimal;

public class MulticookerDto {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    private Integer number;

    public MulticookerDto(String type, String description, Boolean isTouchScreen, Integer numberModes, BigDecimal price, Integer number) {
        this.type = type;
        this.description = description;
        this.isTouchScreen = isTouchScreen;
        this.numberModes = numberModes;
        this.price = price;
        this.number = number;
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

    public static MulticookerDtoBuilder builder() {
        return new MulticookerDtoBuilder();
    }

    public static class MulticookerDtoBuilder {
        private String type;
        private String description;
        private Boolean isTouchScreen;
        private Integer numberModes;
        private BigDecimal price;
        private Integer number;

        MulticookerDtoBuilder(){

        }

        public MulticookerDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public MulticookerDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MulticookerDtoBuilder isTouchScreen(Boolean isTouchScreen) {
            this.isTouchScreen = isTouchScreen;
            return this;
        }

        public MulticookerDtoBuilder numberModes(Integer numberModes) {
            this.numberModes = numberModes;
            return this;
        }

        public MulticookerDtoBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public MulticookerDtoBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public MulticookerDto build(){
            return new MulticookerDto(type, description, isTouchScreen, numberModes, price, number);
        }

        @Override
        public String toString() {
            return "MulticookerDtoBuilder{" +
                    "type='" + type + '\'' +
                    ", description='" + description + '\'' +
                    ", isTouchScreen=" + isTouchScreen +
                    ", numberModes=" + numberModes +
                    ", price=" + price +
                    ", number=" + number +
                    '}';
        }
    }
}
