package by.ita.je.dto;

import by.ita.je.models.Country;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TVDto {
    private String type;
    private String brand;
    private Boolean discount;
    private Integer diagonal;
    private BigDecimal price;
    private Integer number;

    @Singular
    private List<KettleDto> kettleDtos;
    private Country country;
}
