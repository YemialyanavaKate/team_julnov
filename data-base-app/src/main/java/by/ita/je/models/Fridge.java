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
public class Fridge {
    private String type;
    private String description;
    private Boolean discount;
    private Boolean defect;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;
}
