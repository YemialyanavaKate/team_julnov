package by.ita.je.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Multicooker {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number;
    private Character energy;
    private ZonedDateTime registered;
}
