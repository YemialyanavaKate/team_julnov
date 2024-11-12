package by.ita.je.mappers;

import by.ita.je.dto.TVDto;
import by.ita.je.models.TV;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TVMapperToDto {

    public TVDto toDTO(TV tv) {
        if (tv == null) {
            return null;
        }
        return TVDto.builder()
                .type(tv.getType())
                .brand(tv.getBrand())
                .discount(tv.getDiscount())
                .diagonal(tv.getDiagonal())
                .price(tv.getPrice())
                .number(tv.getNumber())
                .country(tv.getCountry())
                .build();
    }
}
