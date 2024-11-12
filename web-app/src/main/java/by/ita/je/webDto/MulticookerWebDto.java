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
public class MulticookerWebDto {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    private Integer number;
}
