package by.ita.je.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
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

    //@Singular
    private List<Multicooker> Multicookers;
    private Kettle kettle;
}
