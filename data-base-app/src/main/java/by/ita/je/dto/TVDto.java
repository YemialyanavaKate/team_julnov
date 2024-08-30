package by.ita.je.dto;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class TVDto {
    private String type;
    private String brand;
    private Boolean discount;
    private Integer diagonal;
    private BigDecimal price;
    private Integer number;

}
