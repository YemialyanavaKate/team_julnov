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

    public static Multicooker toEntity(MulticookerDto multicookerDto){
        return new Multicooker(
                multicookerDto.getType(),
                multicookerDto.getDescription(),
                multicookerDto.getIsTouchScreen(),
                multicookerDto.getNumberModes(),
                multicookerDto.getPrice(),
                multicookerDto.getNumber(),
                null,
                null
        );
    }
}
