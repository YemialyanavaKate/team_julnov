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
public class MulticookerDto {
    private String type;
    private String description;
    private Boolean isTouchScreen;
    private Integer numberModes;
    private BigDecimal price;
    private Integer number;
}
