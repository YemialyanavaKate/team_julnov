package by.ita.je.mapper;

import by.ita.je.dto.to_data_base.KettleDto;
import by.ita.je.dto.to_web.KettleWebDto;
import by.ita.je.models.Kettle;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class KettleMapper {

    public KettleDto toDataBaseDTO(Kettle kettle) {

        return KettleDto.builder()
                .type(kettle.getType())
                .color(kettle.getColor())
                .isElectric(kettle.getIsElectric())
                .isInduction(kettle.getIsInduction())
                .price(kettle.getPrice())
                .number(kettle.getNumber())
                .fridgeDtos(Collections.emptyList())
                .tvDtos(Collections.emptyList())
                .build();
    }

    public KettleWebDto toWebDTO(Kettle kettle) {

        return KettleWebDto.builder()
                .type(kettle.getType())
                .color(kettle.getColor())
                .isElectric(kettle.getIsElectric())
                .isInduction(kettle.getIsInduction())
                .price(kettle.getPrice())
                .number(kettle.getNumber())
                .build();
    }

    public Kettle toEntityFromDataBase(KettleDto kettleDto) {

        return Kettle.builder()
                .type(kettleDto.getType())
                .color(kettleDto.getColor())
                .isElectric(kettleDto.getIsElectric())
                .isInduction(kettleDto.getIsInduction())
                .price(kettleDto.getPrice())
                .number(kettleDto.getNumber())
                .energy(null)
                .registered(null)
                .build();
    }

    public Kettle toEntity(KettleWebDto kettleWebDto) {

        return Kettle.builder()
                .type(kettleWebDto.getType())
                .color(kettleWebDto.getColor())
                .isElectric(kettleWebDto.getIsElectric())
                .isInduction(kettleWebDto.getIsInduction())
                .price(kettleWebDto.getPrice())
                .number(kettleWebDto.getNumber())
                .energy(null)
                .registered(null)
                .build();
    }
}
