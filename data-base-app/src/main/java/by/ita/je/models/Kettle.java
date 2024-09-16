package by.ita.je.models;

import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kettle {
    private String type;
    private String color;
    private Boolean isElectric;
    private Boolean isInduction;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;
}
