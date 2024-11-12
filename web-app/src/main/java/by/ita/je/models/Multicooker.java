package by.ita.je.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Multicooker {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;

    private Fridge fridge;
}
