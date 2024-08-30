package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class FridgeDto {
    private final String type;
    private final String description;
    private final Boolean discount;
    private final Boolean defect;
    private final BigDecimal price;
    private final Integer number;
}





