package by.ita.je.mappers;

import by.ita.je.dto.FridgeDto;
import by.ita.je.dto.KettleDto;
import by.ita.je.dto.MulticookerDto;
import by.ita.je.models.Fridge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FridgeMapperToDto {
    @Autowired
    private MulticookerMapperToDto multicookerMapperToDto;
    @Autowired
    private KettleMapperToDto kettleMapperToDto;

    public FridgeDto toDTO(Fridge fridge) {
        if (fridge == null) {
            return null;
        }

        List<MulticookerDto> multicookerDtos = fridge.getMulticookers() != null ?
                fridge.getMulticookers()
                        .stream()
                        .map(multicookerMapperToDto::toDTO)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        KettleDto kettleDto = fridge.getKettle() != null ?
                kettleMapperToDto.toDTO(fridge.getKettle()) : null;

        return FridgeDto.builder()
                .type(fridge.getType())
                .description(fridge.getDescription())
                .discount(fridge.getDiscount())
                .defect(fridge.getDefect())
                .price(fridge.getPrice())
                .number(fridge.getNumber())
                .multicookerDtos(multicookerDtos)
                .kettleDto(kettleDto)
                .build();
    }

    public Fridge toEntity(FridgeDto fridgeDto) {
        if (fridgeDto == null) {
            return null;
        }
        return Fridge.builder()
                .type(fridgeDto.getType())
                .description(fridgeDto.getDescription())
                .discount(fridgeDto.getDiscount())
                .defect(fridgeDto.getDefect())
                .price(fridgeDto.getPrice())
                .number(fridgeDto.getNumber())
                .energy(null)
                .registered(null)
                .Multicookers(null)
                .kettle(null)
                .build();
    }
}
