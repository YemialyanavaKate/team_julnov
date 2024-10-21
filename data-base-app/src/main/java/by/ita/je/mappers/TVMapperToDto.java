package by.ita.je.mappers;

import by.ita.je.dto.TVDto;
import by.ita.je.models.TV;
import org.springframework.stereotype.Component;

@Component
public class TVMapperToDto {

    public TVDto toDTO(TV tv) {
        return new TVDto(
                tv.getType(),
                tv.getBrand(),
                tv.getDiscount(),
                tv.getDiagonal(),
                tv.getPrice(),
                tv.getNumber()
        );
    }
}
