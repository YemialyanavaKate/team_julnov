package by.ita.je.mappers;

import by.ita.je.dto.MulticookerDto;
import by.ita.je.models.Multicooker;
import org.springframework.stereotype.Component;

@Component
public class MulticookerMapperToDto {

    public MulticookerDto toDTO(Multicooker multicooker) {
        if (multicooker == null) {
            return null;
        }
        return MulticookerDto.builder()
                .type(multicooker.getType())
                .description(multicooker.getDescription())
                .isTouchScreen(multicooker.getIsTouchScreen())
                .numberModes(multicooker.getNumberModes())
                .price(multicooker.getPrice())
                .number(multicooker.getNumber())
                .build();
    }
}
