package by.ita.je.webDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KettleWebDto {
    private String type;
    private String color;
    private Boolean isElectric;
    private Boolean isInduction;
    private BigDecimal price;
    private Integer number;
}
