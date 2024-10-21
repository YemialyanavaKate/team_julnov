package by.ita.je.mappers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.models.Multicooker;
import org.springframework.stereotype.Component;

@Component
public class MulticookerMapperToDto {

    public MulticookerDto toDTO(Multicooker multicooker) {
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
