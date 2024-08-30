package by.ita.je.models;

import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
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

}
