package by.ita.je.mappers;

import by.ita.je.dto.KettleDto;
import by.ita.je.models.Kettle;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class KettleMapperToDto {
    @Autowired
    private TVMapperToDto tvMapperToDto;

    public KettleDto toDTO(Kettle kettle) {
        if (kettle == null) {
            return null;
        }
        return KettleDto.builder()
                .type(kettle.getType())
                .color(kettle.getColor())
                .isElectric(kettle.getIsElectric())
                .isInduction(kettle.getIsInduction())
                .price(kettle.getPrice())
                .number(kettle.getNumber())
                .tvDtos(kettle.getListTV()
                        .stream()
                        .map(tvMapperToDto::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
