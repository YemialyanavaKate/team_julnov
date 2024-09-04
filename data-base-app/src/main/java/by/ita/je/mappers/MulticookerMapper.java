package by.ita.je.mappers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.models.Multicooker;

public class MulticookerMapper {

    public static MulticookerDto toDTO(Multicooker multicooker){
        return new MulticookerDto(
                multicooker.getType(),
                multicooker.getDescription(),
                multicooker.getIsTouchScreen(),
                multicooker.getNumberModes(),
                multicooker.getPrice(),
                multicooker.getNumber()
        );
    }
}
