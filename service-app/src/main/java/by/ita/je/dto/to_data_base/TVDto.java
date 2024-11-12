package by.ita.je.dto.to_data_base;

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
    private List<KettleDto> kettleDtos;
    private CountryDto countryDto;
}
