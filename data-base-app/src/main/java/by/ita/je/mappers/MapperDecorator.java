package by.ita.je.mappers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.models.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MapperDecorator {
    private final FridgeMapperToDto fridgeMapperToDto;
    private final KettleMapperToDto kettleMapperToDto;
    private final MulticookerMapperToDto multicookerMapperToDto;
    private final TVMapperToDto tvMapperToDto;

    public FridgeDto map(Fridge fridge) {
        FridgeDto dto = fridgeMapperToDto.toDTO(fridge);
        dto.setMulticookerDtos(
                fridge.getMulticookers()
                        .stream()
                        .map(multicookerMapperToDto::toDTO)
                        .collect(Collectors.toList())
        );
        dto.setKettleDto(kettleMapperToDto.toDTO(fridge.getKettle()));
        return dto;
    }
}
