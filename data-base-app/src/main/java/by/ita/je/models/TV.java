package by.ita.je.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TV {
    private String type;
    private String brand;
    private Boolean discount;
    private Integer diagonal;
    private BigDecimal price;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;
}
