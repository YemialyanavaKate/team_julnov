package by.ita.je.mappers;

import by.ita.je.webDto.MulticookerWebDto;
import by.ita.je.models.Multicooker;
import org.springframework.stereotype.Component;

@Component
public class MulticookerMapperToWebDto {

    public MulticookerWebDto toDTO(Multicooker multicooker) {
        if (multicooker == null) {
            return null;
        }
        return MulticookerWebDto.builder()
                .type(multicooker.getType())
                .description(multicooker.getDescription())
                .isTouchScreen(multicooker.getIsTouchScreen())
                .numberModes(multicooker.getNumberModes())
                .price(multicooker.getPrice())
                .number(multicooker.getNumber())
                .build();
    }
}
