package by.ita.je.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FridgeDto {
    private String type;
    private String description;
    private Boolean discount;
    private Boolean defect;
    private BigDecimal price;
    private Integer number;
    @Singular
    private List<MulticookerDto> multicookerDtos;
    private KettleDto kettleDto;

}





