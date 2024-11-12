package by.ita.je.webDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FridgeWebDto {
    private String type;
    private String description;
    private Boolean discount;
    private Boolean defect;
    private BigDecimal price;
    private Integer number;
    private List<MulticookerWebDto> multicookerWebDtos;
    private KettleWebDto kettleWebDto;
}





