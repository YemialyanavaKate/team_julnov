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
public class TV {
    private String type;
    private String brand;
    private Boolean discount;
    private Integer diagonal;
    private BigDecimal price;
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;
}
