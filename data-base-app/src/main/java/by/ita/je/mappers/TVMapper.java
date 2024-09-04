package by.ita.je.mappers;

import by.ita.je.dto.TVDto;
import by.ita.je.models.TV;

public class TVMapper {

    public static TVDto toDTO(TV multicooker){
        return new TVDto(
                multicooker.getType(),
                multicooker.getBrand(),
                multicooker.getDiscount(),
                multicooker.getDiagonal(),
                multicooker.getPrice(),
                multicooker.getNumber()
        );
    }
}
