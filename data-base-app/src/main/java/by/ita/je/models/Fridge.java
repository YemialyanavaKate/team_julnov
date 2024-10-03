package by.ita.je.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fridge {
    private String type;
    private String description;
    private Boolean discount;
    private Boolean defect;
    private BigDecimal price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;
}
