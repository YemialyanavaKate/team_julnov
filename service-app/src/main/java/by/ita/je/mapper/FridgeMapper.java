package by.ita.je.mapper;

import by.ita.je.dto.to_data_base.FridgeDto;
import by.ita.je.dto.to_data_base.KettleDto;
import by.ita.je.dto.to_data_base.MulticookerDto;
import by.ita.je.dto.to_web.FridgeWebDto;
import by.ita.je.dto.to_web.KettleWebDto;
import by.ita.je.dto.to_web.MulticookerWebDto;
import by.ita.je.models.Fridge;
import by.ita.je.models.Kettle;
import by.ita.je.models.Multicooker;
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

    public FridgeDto toDataBaseDto(Fridge fridge) {

        KettleDto kettleDto = fridge.getKettle() != null ?
                kettleMapper.toDataBaseDTO(fridge.getKettle()) : null;

        List<MulticookerDto> multicookerDtos = fridge.getMulticookers() != null ?
                fridge.getMulticookers()
                        .stream()
                        .map(multicookerMapper::toDataBaseDTO)
                        .collect(Collectors.toList())
                : Collections.emptyList();

        return FridgeDto.builder()
                .type(fridge.getType())
                .description(fridge.getDescription())
                .discount(fridge.getDiscount())
                .defect(fridge.getDefect())
                .price(fridge.getPrice())
                .number(fridge.getNumber())
                .kettleDto(kettleDto)
                .multicookerDtos(multicookerDtos)
                .build();
    }

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

    public Fridge toEntityFromDataBase(FridgeDto fridgeDto) {

        Kettle kettle = fridgeDto.getKettleDto() != null ?
                kettleMapper.toEntityFromDataBase(fridgeDto.getKettleDto()) : null;

        List<Multicooker> multicookers = fridgeDto.getMulticookerDtos() != null ?
                fridgeDto.getMulticookerDtos()
                        .stream()
                        .map(multicookerMapper::toEntityFromDataBase)
                        .toList()
                : Collections.emptyList();

        return Fridge.builder()
                .type(fridgeDto.getType())
                .description(fridgeDto.getDescription())
                .discount(fridgeDto.getDiscount())
                .defect(fridgeDto.getDefect())
                .price(fridgeDto.getPrice())
                .number(fridgeDto.getNumber())
                .energy(null)
                .registered(null)
                .Multicookers(multicookers)
                .kettle(kettle)
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
                .kettle(kettle)
                .build();
    }
}
