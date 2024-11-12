package by.ita.je.mapper;

import by.ita.je.dto.to_data_base.MulticookerDto;
import by.ita.je.dto.to_web.MulticookerWebDto;
import by.ita.je.models.Multicooker;
import org.springframework.stereotype.Component;

@Component
public class MulticookerMapper {
    public MulticookerDto toDataBaseDTO(Multicooker multicooker) {

        return MulticookerDto.builder()
                .type(multicooker.getType())
                .description(multicooker.getDescription())
                .isTouchScreen(multicooker.getIsTouchScreen())
                .numberModes(multicooker.getNumberModes())
                .price(multicooker.getPrice())
                .number(multicooker.getNumber())
                .fridgeDto(null)
                .build();
    }

    public MulticookerWebDto toDTO(Multicooker multicooker) {

        return MulticookerWebDto.builder()
                .type(multicooker.getType())
                .description(multicooker.getDescription())
                .isTouchScreen(multicooker.getIsTouchScreen())
                .numberModes(multicooker.getNumberModes())
                .price(multicooker.getPrice())
                .number(multicooker.getNumber())
                .build();
    }

    public Multicooker toEntityFromDataBase(MulticookerDto multicookerDto) {

        return Multicooker.builder()
                .type(multicookerDto.getType())
                .description(multicookerDto.getDescription())
                .isTouchScreen(multicookerDto.getIsTouchScreen())
                .numberModes(multicookerDto.getNumberModes())
                .price(multicookerDto.getPrice())
                .number(multicookerDto.getNumber())
                .energy(null)
                .registered(null)
                .build();
    }

    public Multicooker toEntity(MulticookerWebDto multicookerWebDto) {

        return Multicooker.builder()
                .type(multicookerWebDto.getType())
                .description(multicookerWebDto.getDescription())
                .isTouchScreen(multicookerWebDto.getIsTouchScreen())
                .numberModes(multicookerWebDto.getNumberModes())
                .price(multicookerWebDto.getPrice())
                .number(multicookerWebDto.getNumber())
                .energy(null)
                .registered(null)
                .build();
    }
}
