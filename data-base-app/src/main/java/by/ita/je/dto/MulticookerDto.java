package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MulticookerDto {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    private Integer number;

    @JsonIgnore
    private FridgeDto fridgeDto;
}
