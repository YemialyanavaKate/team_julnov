package by.ita.je.mappers;

import by.ita.je.models.Kettle;
import by.ita.je.models.Multicooker;
import by.ita.je.webDto.FridgeWebDto;
import by.ita.je.webDto.KettleWebDto;
import by.ita.je.webDto.MulticookerWebDto;
import by.ita.je.models.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FridgeMapper {

    private final KettleMapper kettleMapper;
    private final MulticookerMapper multicookerMapper;

    public FridgeWebDto toWebDto(Fridge fridge) {

        KettleWebDto kettleWebDto = fridge.getKettle() != null ?
                kettleMapper.toWebDTO(fridge.getKettle()) : null;

        List<MulticookerWebDto> multicookerWebDtos = fridge.getMulticookers() != null ?
                fridge.getMulticookers()
                        .stream()
                        .map(multicookerMapper::toDTO)
                        .collect(Collectors.toList())
                : Collections.emptyList();

        return FridgeWebDto.builder()
                .type(fridge.getType())
                .description(fridge.getDescription())
                .discount(fridge.getDiscount())
                .defect(fridge.getDefect())
                .price(fridge.getPrice())
                .number(fridge.getNumber())
                .kettleWebDto(kettleWebDto)
                .multicookerWebDtos(multicookerWebDtos)
                .build();
    }

    public Fridge toEntity(FridgeWebDto fridgeWebDto) {

        Kettle kettle = fridgeWebDto.getKettleWebDto() != null ?
                kettleMapper.toEntity(fridgeWebDto.getKettleWebDto()) : null;

        List<Multicooker> multicookers = fridgeWebDto.getMulticookerWebDtos() != null ?
                fridgeWebDto.getMulticookerWebDtos()
                        .stream()
                        .map(multicookerMapper::toEntity)
                        .toList()
                : Collections.emptyList();

        return Fridge.builder()
                .type(fridgeWebDto.getType())
                .description(fridgeWebDto.getDescription())
                .discount(fridgeWebDto.getDiscount())
                .defect(fridgeWebDto.getDefect())
                .price(fridgeWebDto.getPrice())
                .number(fridgeWebDto.getNumber())
                .energy(null)
                .registered(null)
                .Multicookers(multicookers)
                .kettle(kettleMapper.toEntity(fridgeWebDto.getKettleWebDto()))
                .build();
    }
}
