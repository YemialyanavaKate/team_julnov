package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @JsonIgnore
    private List<FridgeDto> fridgeDtos;
    @JsonIgnore
    private List<TVDto> tvDtos;

}
