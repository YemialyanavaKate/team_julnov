package by.ita.je.dto.to_data_base;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class KettleDto {
    private String type;
    private String color;
    private Boolean isElectric;
    private Boolean isInduction;
    private BigDecimal price;
    private Integer number;
    private TVDto tvDto;

    @JsonIgnore
    private List<FridgeDto> fridgeDtos;
    @JsonIgnore
    private List<TVDto> tvDtos;
}
